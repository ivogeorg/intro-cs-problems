package edu.msud.cs.cs1.boardgame;

public final class Grunt extends GamePiece {

    public Grunt(Position pos, int life) {
        super(pos, life);
    }

    public Move move() {
        return new Move(-1, +2);
    }
}
