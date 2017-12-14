package edu.msud.cs.cs1.boardgame;

public abstract class GamePiece {

    private int life;
    private boolean isAlive;
    private Position pos;

    public GamePiece(Position pos, int life) {
        this.life = life;
        this.isAlive = true;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }

    public void setPosition(Position pos) {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }

    public void age() {
        if (life > 0) life--;
        if (life == 0) isAlive = false;
    }

    public void age(int years) {
        if (life > 0) life -= years;
        if (life <= 0) {
            life = 0;
            isAlive = false;
        }
    }

    public void perish() {
        life = 0;
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    abstract Move move();
}
