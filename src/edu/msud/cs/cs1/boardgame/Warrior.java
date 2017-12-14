package edu.msud.cs.cs1.boardgame;

public final class Warrior extends GamePiece {
    private static final double LONGEVITY = 1.4;

    public Warrior(int life) {
        super((int) Math.ceil(life*LONGEVITY));
    }

    public Move move() {
        return new Move(2, 1);
    }
}
