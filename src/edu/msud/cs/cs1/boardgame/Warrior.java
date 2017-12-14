package edu.msud.cs.cs1.boardgame;

public final class Warrior extends GamePiece {
    private static final double LONGEVITY = 1.4;

    public Warrior(Position pos, int life) {
        super(pos, (int) Math.ceil(life*LONGEVITY));
    }

    public Move move() {
        return new Move(2, 1);
    }
}
