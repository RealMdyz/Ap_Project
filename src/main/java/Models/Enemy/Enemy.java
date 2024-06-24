package Models.Enemy;

import Models.*;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Enemy extends ObjectsInGame implements LocalRouting, Aggression, Moveable {

    private int collectibleNumber = 1;
    private int xpForEachCollectible = 5;
    private int numSides;
    private int power = 6;
    Point[] points;

    private boolean hovering;

    public Enemy(int x, int y, int hp, int collectibleNumber, int xpForEachCollectible, int numSides, int power, boolean hovering, GameFrame frame) {
        super(x, y, hp, frame);
        this.points = makePoint();
        this.power = power;
        this.numSides = numSides;
        this.collectibleNumber = collectibleNumber;
        this.xpForEachCollectible = xpForEachCollectible;
        this.hovering = hovering;
    }
    public Point[] makePoint(){
        if(getHp() == 10){
            Point[] points1 = new Point[4];
            points1[0] = new Point();
            points1[1] = new Point();
            points1[2] = new Point();
            points1[3] = new Point();

            points1[0].setLocation(this.getX(), this.getY());

            points1[1].setLocation(this.getX() + Constant.getWidthOfSquarantine(), this.getY());

            points1[2].setLocation(this.getX() + Constant.getWidthOfSquarantine(), this.getY() + Constant.getHeightOfSquarantine());

            points1[3].setLocation(this.getX(), this.getY() + Constant.getHeightOfSquarantine());

            //System.out.println(points1[0].getX() + " " + points1[1].getX() + " " + points1[2].getX() + " " + points1[3].getX());

            return points1;
        }
        else{
            Point[] points1 = new Point[3];
            points1[0] = new Point();
            points1[0].x = getX();
            points1[0].y = getY() + Constant.getHeightOfTrighrath();

            points1[1] = new Point();
            points1[1].x = getX() + (int)(Constant.getWidthOfTrighrath() / 2);
            points1[1].y = getY();

            points1[2] = new Point();
            points1[2].x = getX() + Constant.getWidthOfTrighrath();
            points1[2].y = getY() + Constant.getHeightOfTrighrath();

            return points1;
        }
    }

    @Override
    public void aggression() {

    }

    @Override
    public void localRouting(int xEpsilon, int yEpsilon) {

    }

    @Override
    public void move(int xLimit, int yLimit) {

    }

    public int getCollectibleNumber() {
        return collectibleNumber;
    }

    public void setCollectibleNumber(int collectibleNumber) {
        this.collectibleNumber = collectibleNumber;
    }

    public int getXpForEachCollectible() {
        return xpForEachCollectible;
    }

    public void setXpForEachCollectible(int xpForEachCollectible) {
        this.xpForEachCollectible = xpForEachCollectible;
    }

    public int getNumSides() {
        return numSides;
    }

    public void setNumSides(int numSides) {
        this.numSides = numSides;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
    public void addX(int amountForAdd){
        this.setX(this.getX() + amountForAdd);
        for (Point point : this.getPoints()){
            point.setLocation(point.getX() + amountForAdd, point.getY());
        }
    }
    public void addY(int amountForAdd){
        this.setY(this.getY() + amountForAdd);
        for (Point point : this.getPoints()){
            point.setLocation(point.getX(), point.getY() + amountForAdd);
        }
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
