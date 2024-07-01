package Models.Enemy;

import Models.ObjectsInGame;
import View.Game.GameFrame;

public class Aoe{
    private int x, y, duration;
    private int width, height;
    private long startOFMe;
    private long  lastAttackFromMe = 0;

    public Aoe(int x, int y, int duration, int width, int height) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        this.height = height;
        this.width = width;
        startOFMe = System.currentTimeMillis();

    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getDuration() { return duration; }

    public boolean isExpired() {
        return System.currentTimeMillis() - startOFMe >= duration;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getLastAttackFromMe() {
        return lastAttackFromMe;
    }

    public void setLastAttackFromMe(long lastAttackFromMe) {
        this.lastAttackFromMe = lastAttackFromMe;
    }
}