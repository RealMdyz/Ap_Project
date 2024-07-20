package Models.Epsilon;

import Models.Constant;
import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Shot extends ObjectsInGame implements Moveable {
    private int xVelocity = 0;
    private int yVelocity = 0;
    Constant constant;
    boolean solb;

    private int power;

    public Shot(int x, int y, int power, GameFrame frame, boolean solb) {
        super(x, y, 1, frame);
        this.solb = solb;
        this.power = power;
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
    public void setRandomVAboutTwoObjectInGame(ObjectsInGame Start, ObjectsInGame End){
        double angle = Math.atan2(- Start.getY() + End.getY(), - Start.getX() + End.getX());
        double velocityX = Math.cos(angle) * Constant.getSpeedOfShot(); // Adjust the velocity as needed
        double velocityY = Math.sin(angle) * Constant.getSpeedOfShot(); // Adjust the velocity as needed
        setxVelocity((int)velocityX);
        setyVelocity((int)velocityY);
    }
    public void setRandomV() {
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI; // زاویه تصادفی بین 0 و 2π (360 درجه)

        double velocityX = Math.cos(angle) * Constant.getSpeedOfShot(); // تنظیم سرعت در جهت x
        double velocityY = Math.sin(angle) * Constant.getSpeedOfShot(); // تنظیم سرعت در جهت y

        setxVelocity((int) velocityX);
        setyVelocity((int) velocityY);
    }


    @Override
    public void move() {
        this.setX(this.getX() + this.xVelocity);
        this.setY(this.getY() + this.yVelocity);
        this.repaint();
        this.getCurrentFrame().repaint();
    }
    public boolean checkRemovedBoard(int xLimit, int yLimit){
        if(this.getX() < 0 || this.getY() < 0 || this.getY() > yLimit || this.getX() > xLimit)
            return true;
        return false;
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

    public boolean isSolb() {
        return solb;
    }

    public void setSolb(boolean solb) {
        this.solb = solb;
    }
}
