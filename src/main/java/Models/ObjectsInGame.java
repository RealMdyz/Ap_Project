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
    private int xVelocityImpact = 0;
    private int yVelocityImpact = 0;

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

        // Update the object's position using the velocity components
        // Instead of updating position directly, adjust velocity gradually
        setxVelocityImpact(getxVelocityImpact() + (int) vx);
        setyVelocityImpact(getyVelocityImpact() + (int) vy);

        // Apply additional force based on the distance from the impact point
        double impactStrength = 1.0; // Adjust impact strength as needed
        double distanceFactor = 1.0 - (distance / Constant.MAX_DISTANCE); // MAX_DISTANCE is the maximum distance of impact influence
        double finalVx = vx * impactStrength * distanceFactor;
        double finalVy = vy * impactStrength * distanceFactor;

        // Update the object's velocity based on the additional force
        setxVelocity(getxVelocity() + (int) finalVx);
        setyVelocity(getyVelocity() + (int) finalVy);
        System.out.println(xImpact + " " + yImpact + " " + getX() + " " + getY() + " " + vx + " " + vy);;

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

    public int getxVelocityImpact() {
        return xVelocityImpact;
    }

    public void setxVelocityImpact(int xVelocityImpact) {
        this.xVelocityImpact = xVelocityImpact;
    }

    public int getyVelocityImpact() {
        return yVelocityImpact;
    }

    public void setyVelocityImpact(int yVelocityImpact) {
        this.yVelocityImpact = yVelocityImpact;
    }

}