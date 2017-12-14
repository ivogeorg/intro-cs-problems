package edu.msud.cs.cs1.boardgame;

public final class Move {
    public int ud; // up-down (aka vertical)
    public int lr; // left-right (aka horizontal)

    public Move(int ud, int lr) {
        this.ud = ud;
        this.lr = lr;
    }
}
