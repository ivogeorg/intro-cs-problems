package edu.msud.cs.cs1.boardgame;

public final class Grunt extends GamePiece {

    public Grunt(Position pos, int life) {
        super(pos, life);
    }

    public Move move() {
        // moves like a knight in chess
        int x = (int) Math.pow(-1, Math.round(Math.random()));
        int y = (int) Math.pow(-1, Math.round(Math.random()));
        if (Math.random() < 0.5) x *= 2; else y *= 2;
        return new Move(x, y);
    }
}
