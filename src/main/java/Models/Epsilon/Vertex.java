package Models.Epsilon;

import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Vertex extends ObjectsInGame implements Moveable {
    private int x;
    private int y;
    private int power;
    private Color color;
    private long lastAttackFromMe = 0;
    private double xFake, yFake;

    public Vertex(int x, int y, int power, GameFrame frame) {
        super(x, y , 1, frame);
        this.x = x;
        this.y = y;
        this.power = power;
        xFake = this.getX();
        yFake = this.getY();
        this.setHeight(15);
        this.setWidth(15);
        setSize(this.getWidth(), this.getHeight());
        this.setVisible(true);
        this.color = Color.RED;
        background = MyProjectData.getProjectData().getVertexOnEpsilon();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public void move(){
        int xLimit = currentFrame.getWidth();
        int yLimit = currentFrame.getHeight();
        if(this.getX() <= 0  && this.getxVelocity() > 0){
            xFake += (this.getxVelocity());
            //     this.setX(this.getX() + this.getxVelocity());
            this.setX((int)xFake);
        }
        else if(this.getX() >= xLimit - this.getWidth() - 20 && this.getxVelocity() < 0){
            xFake += (this.getxVelocity());
            //     this.setX(this.getX() + this.getxVelocity());
            this.setX((int)xFake);
        }
        else if(this.getX() >= 0 && this.getX() <= xLimit - this.getWidth() - 20){
            xFake += (this.getxVelocity());
            //    this.setX(this.getX() + this.getxVelocity());
            this.setX((int)xFake);
        }
        if(this.getY() <= 0  && this.getyVelocity() > 0){
            yFake += this.getyVelocity();
            //    this.setY(this.getY() + this.getyVelocity());
            this.setY((int)yFake);
        }
        else if(this.getY() >= yLimit - this.getHeight() && this.getyVelocity() < 0){
            yFake += this.getyVelocity();
            //     this.setY(this.getY() + this.getyVelocity());
            this.setY((int)yFake);
        }
        else if(this.getY() >= 0 && this.getY() <= yLimit - this.getHeight()){
            yFake += this.getyVelocity();
       //     this.setY(this.getY() + this.getyVelocity());
            this.setY((int)yFake);
        }


    }

    public double getxFake() {
        return xFake;
    }

    public void setxFake(double xFake) {
        this.xFake = xFake;
        setX((int)xFake);
    }

    public double getyFake() {
        return yFake;
    }

    public void setyFake(double yFake) {
        this.yFake = yFake;
        setY((int)yFake);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.xFake = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.yFake = y;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public long getLastAttackFromMe() {
        return lastAttackFromMe;
    }

    public void setLastAttackFromMe(long lastAttackFromMe) {
        this.lastAttackFromMe = lastAttackFromMe;
    }
}

