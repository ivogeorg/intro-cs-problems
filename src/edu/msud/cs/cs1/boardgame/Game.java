package edu.msud.cs.cs1.boardgame;

import java.util.ArrayList;

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

        board = new ArrayList<>(height*width);
        for (int i=0; i<height*width; i++)
            board.add(new ArrayList<GamePiece>());

        while (initNumPieces > 0)
            if (Math.random() <= GRUNT_PREVALENCE)
                board.get((int) Math.floor(Math.random()*board.size())).add(new Grunt(INIT_LIFE));
            else
                board.get((int) Math.floor(Math.random()*board.size())).add(new Warrior(INIT_LIFE));
    }

    public void play() {
        while (anyoneLeft()) {
            Game.showBoard(this);
            letThemMarch();
            doBattle();
            aYearGoesBy();
            buryDead();
            try {
                Thread.sleep(PAUSE_BETWEEN_ROUNDS);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Game over!");
    }

    public static void showBoard(Game g) {

        // TODO: Show in grid form, counting warriors and grunts
        // |---------|---------|---------|---------|---------|
        // |W-12 G- 6|W- 0 G- 3|W- 1 G- 0|W- 5 G- 5|W- 0 G- 0|
        // |---------|---------|---------|---------|---------|
        // |W- 0 G- 1|W- 2 G- 3|W- 0 G- 0|W- 0 G- 1|W- 2 G- 4|
        // |---------|---------|---------|---------|---------|
        // |W- 0 G- 0|W- 0 G- 0|W- 1 G- 2|W- 1 G- 6|W-22 G-24|
        // |---------|---------|---------|---------|---------|
        // |W- 7 G- 8|W- 0 G- 4|W- 3 G- 0|W- 0 G- 1|W- 2 G- 1|
        // |---------|---------|---------|---------|---------|
        // |W- 0 G- 5|W- 2 G- 1|W- 1 G- 1|W- 0 G- 0|W- 1 G- 4|
        // |---------|---------|---------|---------|---------|
    }

    private boolean anyoneLeft() {
        for (ArrayList<GamePiece> position: board)
            if (position.size() > 0) return true;
        return false;
    }

    private void buryDead() {
        for (ArrayList<GamePiece> position: board)
            for (GamePiece piece: position)
                if (!piece.isAlive()) position.remove(piece);
    }

    private void letThemMarch() {
        for (ArrayList<GamePiece> position: board)
            for (GamePiece piece: position) {
                Move move = piece.move();

                // TODO: check if legal, decide on wrap-around
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
        // if there are non-zero of each kind
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
