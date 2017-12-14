package edu.msud.cs.cs1.boardgame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Game {

    public static final double GRUNT_PREVALENCE = 0.6;
    public static final int INIT_LIFE = 15;

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

    public boolean turn() {

        if (anyoneLeft()) {

            // TODO: turn

        }

        return false;
    }

    public static void showBoard(Game g) {

        // TODO

    }

    private boolean anyoneLeft() {
        for (ArrayList<GamePiece> position: board)
            if (position.size() > 0) return true;
        return false;
    }

    public static void main(String[] args) {


    }
}
