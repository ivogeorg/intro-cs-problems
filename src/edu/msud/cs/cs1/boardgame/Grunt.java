package edu.msud.cs.cs1.boardgame;

public final class Grunt extends GamePiece {

    public Grunt(int life) {
        super(life);
    }

    public Move move() {
        return new Move(-1, +2);
    }
}
