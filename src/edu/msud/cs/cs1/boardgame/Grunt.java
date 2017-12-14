package edu.msud.cs.cs1.boardgame;

public final class Grunt extends GamePiece {

    public Grunt(int x, int y, int life) {
        super(x, y, life);
    }

    public Move move() {
        return new Move(-1, +2);
    }
}
