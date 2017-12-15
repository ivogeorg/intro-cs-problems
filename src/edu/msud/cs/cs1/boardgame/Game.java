package edu.msud.cs.cs1.boardgame;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {

    public static final double GRUNT_PREVALENCE = 0.55;
    public static final int INIT_LIFE = 30;
    public static final int PAUSE_BETWEEN_ROUNDS = 1000; // in milliseconds
    public static final int BATTLE_AGING = 2;

    private ArrayList<ArrayList<GamePiece>> board;
    private int height, width; // needed for display and moves


    public Game(int height, int width, int initNumPieces) {
        this.height = height;
        this.width = width;

        board = new ArrayList<>(height * width);
        for (int i=0; i<height * width; i++)
            board.add(new ArrayList<>());

        while (initNumPieces > 0) {
            int x = (int) Math.floor(Math.random() * width);
            int y = (int) Math.floor(Math.random() * height);
            Position pos = new Position(x, y);
            GamePiece piece = (Math.random() < GRUNT_PREVALENCE) ?
                    new Grunt(pos, INIT_LIFE) :
                    new Warrior(pos, INIT_LIFE);
            board.get(y * width + x).add(piece);
            initNumPieces --;
        }
    }

    public void play() {
        int rounds = 0;
        Population init = countAllPieces();
        System.out.printf("Initial population: Grunts-%d, Warriors-%d\n", init.x, init.y);
        while (anyOpponentsLeft()) {
            Game.showBoard(this);
            doBattle();
            buryTheDead();
            letThemForage();
            aYearGoesBy();
            rounds ++;
            Population rnd = countAllPieces();
            System.out.printf("Survivors: Grunts-%d, Warriors-%d\n", rnd.x, rnd.y);
            try {
                Thread.sleep(PAUSE_BETWEEN_ROUNDS);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Game over! Game took %d rounds.\n", rounds);
        Population fin = countAllPieces();
        if (fin.x == fin.y)
            System.out.println("Mutual anihilation... :(");
        else if (fin.x == 0)
            System.out.println("Warriors win!");
        else
            System.out.println("Grunts win!");
    }

    public static void showBoard(Game g) {
        // Show in grid form, counting warriors and grunts (obj instanceof c)
        // grunts on the left, warriors on the right
        // |-------|-------|-------|-------|-------|
        // | 12-  6|  0-  3|  1-  0|  5-  5|  0-  0|
        // |-------|-------|-------|-------|-------|
        // |  0-  1|  2-  3|  0-  0|  0-  1|  2-  4|
        // |-------|-------|-------|-------|-------|
        // |  0-  0|  0-  0|  1-  2|  1-  6| 22- 24|
        // |-------|-------|-------|-------|-------|
        // |  7-  8|  0-  4|  3-  0|  0-  1|  2-  1|
        // |-------|-------|-------|-------|-------|
        // |  0-  5|  2-  1|  1-  1|  0-  0|  1-  4|
        // |-------|-------|-------|-------|-------|
        StringBuilder display = new StringBuilder();
        for (int y = 0; y < g.height; y++) {
            for (int x = 0; x < g.width; x++) display.append("|-------");
            display.append("|\n");
            for (int x = 0; x < g.width; x++) {
                ArrayList<GamePiece> position = g.board.get(y * g.width + x);
                // count grunts and warriors
                Population pop = g.countPosPieces(position);
                int grunts = pop.x, warriors = pop.y;
                display.append(String.format("|%3d-%3d", grunts, warriors));
            }
            display.append("|\n");
        }
        for (int x = 0; x < g.width; x++) display.append("|-------");
        display.append("|\n");
        System.out.println(display);
    }

    private boolean anyOpponentsLeft() {
        Population pop = countAllPieces();
        return !(pop.x == 0 || pop.y == 0);
    }

    private void buryTheDead() {
        for (ArrayList<GamePiece> position: board)
            position.removeIf((GamePiece piece) -> !piece.isAlive());
    }

    private void letThemForage() {
        for (int x=0; x<width; x++) {
            for (int y = 0; y < height; y++) {
                ArrayList<GamePiece> position = board.get(y * width + x);
                Iterator<GamePiece> iter = position.iterator();
                while (iter.hasNext()) {
                    GamePiece piece = iter.next();
                    Move move = piece.move();
                    // NOTE: using wraparound on the board
                    // the following avoids negative indices
                    int xx = (piece.getPosition().x + width + move.x) % width;
                    int yy = (piece.getPosition().y + height + move.y) % height;
                    ArrayList<GamePiece> destination = board.get(yy * width + xx);
                    if (destination != position) {
                        iter.remove();
                        destination.add(piece);
                        piece.setPosition(new Position(xx, yy));
                    }
                }
            }
        }
    }

    private void aYearGoesBy() {
        for (ArrayList<GamePiece> position: board)
            for (GamePiece piece: position)
                if (piece.isAlive()) piece.age();
    }

    private void doBattle() {
        // Description in pseudocode:
        // count warriors and grunts
        // if there are non-zero of each kind (obj instanceof c)
        //   if they have different counts
        //     remove all of the outnumbered kind (use perish())
        //     battle-age the survivors
        //   else (they have equal counts)
        //     battle-age all
        for (ArrayList<GamePiece> position: board) {
            Population pop = countPosPieces(position);
            int grunts = pop.x, warriors = pop.y;
            if (grunts == warriors) {
                if (grunts > 0)
                    position.forEach((GamePiece piece) -> piece.age(BATTLE_AGING));
            } else if (grunts > warriors) {
                if (warriors > 0)
                    position.removeIf((GamePiece piece) -> piece instanceof Warrior);
                if (position.size() > 0)
                    position.forEach((GamePiece piece) -> piece.age(BATTLE_AGING));
            } else {
                if (grunts > 0)
                    position.removeIf((GamePiece piece) -> piece instanceof Grunt);
                if (position.size() > 0)
                    position.forEach((GamePiece piece) -> piece.age(BATTLE_AGING));
            }
        }
    }

    private Population countPosPieces(ArrayList<GamePiece> position) {
        int grunts = 0, warriors = 0;
        for (GamePiece piece : position) {
            if (piece instanceof Grunt) grunts++;
            else if (piece instanceof Warrior) warriors++;
            else System.err.println("Warning: Unknown subclass of GamePiece");
        }
        return new Population(grunts, warriors);
    }

    private Population countAllPieces() {
        int grunts = 0, warriors = 0;
        for (ArrayList<GamePiece> position: board) {
            Population pop = countPosPieces(position);
            grunts += pop.x;
            warriors += pop.y;
        }
        return new Population(grunts, warriors);
    }

    public static void main(String[] args) {
        Game game = new Game(5, 5, 60);
        game.play();
    }
}
