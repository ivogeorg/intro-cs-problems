package edu.msud.cs.cs1.boardgame;

public final class Warrior extends GamePiece {
    private static final double LONGEVITY = 1.25;
    private static final int MAX_DISTANCE = 5;

    public Warrior(Position pos, int life) {
        super(pos, (int) Math.ceil(life*LONGEVITY));
    }

    public Move move() {
        // moves like a queen in chess
        int mag = (int) Math.ceil(Math.random() * MAX_DISTANCE);
        int x = 0;
        if (Math.random() < 0.33333333) x = -1;
        else if (Math.random() >= 0.66666667) x = 1;
        int y = 0;
        if (Math.random() < 0.33333333) y = -1;
        else if (Math.random() >= 0.66666667) y = 1;
        return new Move(x * mag, y * mag);
    }
}
