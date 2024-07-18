package Models.Enemy.Normal;

import Controller.Game.Intersection;
import Models.ObjectsInGame;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class Aoe extends ObjectsInGame {
    private int x, y, duration, power;
    private int width, height;
    private long startOFMe;
    private long  lastAttackFromMe = 0;

    public Aoe(int x, int y, int duration,int power,  int width, int height, GameFrame gameFrame) {
        super(x, y, 0, gameFrame);
        this.x = x;
        this.y = y;
        this.power = power;
        this.duration = duration;
        this.height = height;
        this.width = width;
        startOFMe = System.currentTimeMillis();
        setOpaque(false); // شفافیت را فعال می‌کند
        setBounds(x, y, width, height);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(255, 0, 0, 100));
        g2D.fillOval(0, 0, width, height);
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        repaint();
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

    public void checkAndReduceTheHP(ObjectsInGame objectsInGame){
        if (Intersection.isInAoE(objectsInGame, this) && System.currentTimeMillis() - this.getLastAttackFromMe() > duration) {
            objectsInGame.setHp(objectsInGame.getHp() - this.power);
            this.setLastAttackFromMe(System.currentTimeMillis());
        }
    }

    public void setLastAttackFromMe(long lastAttackFromMe) {
        this.lastAttackFromMe = lastAttackFromMe;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}