package Models.Enemy;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.EntityType;
import Models.ObjectsInGame;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Aoe extends ObjectsInGame {
    private int x, y, duration, power;
    private int width, height;
    private long startOFMe;
    private long lastAttackFromMe = 0;
    private Color currentColor; // Store the current color
    public Aoe(int x, int y, int duration, int power, int width, int height, GameFrame gameFrame) {
        super(x, y, 0, gameFrame);
        this.x = x;
        this.y = y;
        this.power = power;
        this.duration = duration;
        this.height = height;
        this.width = width;
        startOFMe = System.currentTimeMillis();
        setOpaque(false); // Enable transparency
        setBounds(x, y, width, height);
        currentColor = new Color(255, 0, 0, 100); // Initial color with full opacity
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fade(); // Call fade to update the color
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(currentColor); // Use the current color
        g2D.fillOval(0, 0, width, height);
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        repaint();
    }

    public void fade() {
        long elapsedTime = System.currentTimeMillis() - startOFMe;
        int alpha = 100 - (int)(elapsedTime * 100 / duration); // Calculate transparency based on time
        alpha = Math.max(alpha, 0); // Ensure it doesn't go negative
        currentColor = new Color(255, 0, 0, alpha); // Update the current color with the new alpha
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

    public void checkAndReduceTheHP(ObjectsInGame objectsInGame, EntityType entityType) {
        if (Intersection.isInAoE(objectsInGame, this) && System.currentTimeMillis() - this.getLastAttackFromMe() > duration) {
            objectsInGame.reduceHp(this.power, AttackType.AOE, entityType);
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
