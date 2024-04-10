package Models.Epsilon;

import Models.Constant;
import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Shot extends ObjectsInGame implements Moveable {
    private int xVelocity = 0;
    private int yVelocity = 0;
    Constant constant;

    private int power = 5;

    public Shot(int x, int y) {
        super(x, y, 1);
        this.setHeight(70);
        this.setWidth(70);
        setSize(getWidth(), getHeight());
        background = MyProjectData.getProjectData().getShot();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, 70, 70, null);
        /*
        g2D.fill(new Arc2D.Double(getX() - radius, getY() - radius, 2 * radius, 2 * radius, 0, 360, Arc2D.PIE));
        g2D.dispose();
        */
    }
    public void setV(int x, int y){
        double angle = Math.atan2(- getY() + y, - getX() + x);
        double velocityX = Math.cos(angle) * Constant.getSpeedOfShot(); // Adjust the velocity as needed
        double velocityY = Math.sin(angle) * Constant.getSpeedOfShot(); // Adjust the velocity as needed
        setxVelocity((int)velocityX);
        setyVelocity((int)velocityY);
    }


    @Override
    public void move(int xLimit, int yLimit) {
        this.setX(this.getX() + this.xVelocity);
        this.setY(this.getY() + this.yVelocity);
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
