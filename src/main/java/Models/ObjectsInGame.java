package Models;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ObjectsInGame extends JLabel {


    private int x;
    private int y;
    private int hp = 10;
    private int width;
    private int height;
    private int xVelocity = 0;
    private int yVelocity = 0;
    private double xVelocityImpact = 0;
    private double yVelocityImpact = 0;
    private long impactTime = 0;

    protected BufferedImage background;

    public ObjectsInGame(int x, int y, int hp) {

        this.x = x;
        this.y = y;
        this.hp = hp;

    }

    public void doImpact(int xImpact, int yImpact) {
        // Calculate the direction vector from the impact point to the object's position
        double dx = getX() - xImpact;
        double dy = getY() - yImpact;

        // Calculate the distance from the impact point to the object's position
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Adjust speed as needed
        double speed = Constant.getSpeedOfImpact();

        // Calculate the velocity components based on the distance
        double vx = speed * dx / distance;
        double vy = speed * dy / distance;
        impactTime = System.currentTimeMillis();
        setxVelocityImpact(vx);
        setyVelocityImpact(vy);
    }



    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getxVelocityImpact() {
        return xVelocityImpact;
    }

    public void setxVelocityImpact(double xVelocityImpact) {
        this.xVelocityImpact = xVelocityImpact;
    }

    public double getyVelocityImpact() {
        return yVelocityImpact;
    }

    public void setyVelocityImpact(double yVelocityImpact) {
        this.yVelocityImpact = yVelocityImpact;
    }

    public long getImpactTime() {
        return impactTime;
    }

    public void setImpactTime(long impactTime) {
        this.impactTime = impactTime;
    }
}