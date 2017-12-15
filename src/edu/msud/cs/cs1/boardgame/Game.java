package edu.msud.cs.cs1.boardgame;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {

    public static final double GRUNT_PREVALENCE = 0.6;
    public static final int INIT_LIFE = 15;
    public static final int PAUSE_BETWEEN_ROUNDS = 1000; // in milliseconds
    public static final int BATTLE_AGING = 3;

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
            GamePiece piece = (Math.random() <= GRUNT_PREVALENCE) ?
                    new Grunt(pos, INIT_LIFE) :
                    new Warrior(pos, INIT_LIFE);
            board.get(y * width + x).add(piece);
            initNumPieces --;
        }
    }
    // TODO: javadoc comments for the boardgame package
    public void play() {
        int rounds = 0;
        while (anyoneLeft()) {
            Game.showBoard(this);
            letThemMarch();
            doBattle();
            aYearGoesBy();
            buryDead();
            rounds ++;
            try {
                Thread.sleep(PAUSE_BETWEEN_ROUNDS);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Game over! Game took %d rounds.\n", rounds);
    }

    public static void showBoard(Game g) {
        // Show in grid form, counting warriors and grunts (obj instanceof c)
        // in alphabetic order, grunts-warriors
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
                int grunts = 0, warriors = 0;
                for (GamePiece piece : position) {
                    if (piece instanceof Grunt) grunts++;
                    else if (piece instanceof Warrior) warriors++;
                    else System.err.println("Warning: Unknown subclass of GamePiece");
                }
                display.append(String.format("|%03d-%03d", grunts, warriors));
            }
            display.append("|\n");
        }
        for (int x = 0; x < g.width; x++) display.append("|-------");
        display.append("|\n");
        System.out.println(display);
    }

    private boolean anyoneLeft() {
        for (ArrayList<GamePiece> position: board)
            if (position.size() > 0) return true;
        return false;
    }

    private void buryDead() {
        for (ArrayList<GamePiece> position: board)
            position.removeIf((GamePiece piece) -> !piece.isAlive());
    }

    private void letThemMarch() {
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
                    ArrayList<GamePiece> destination = board.get(y * width + x);
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

        // TODO: For each position
        // count warriors and grunts
        // if there are non-zero of each kind (obj instanceof c)
        //   if they are different numbers
        //     remove all of the outnumbered kind (use perish())
        //     battle-age the survivors
        //   else
        //     battle-age all

    }

    public static void main(String[] args) {
        Game game = new Game(5, 5, 25);
        game.play();
    }
}
