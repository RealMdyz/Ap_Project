package Models.Enemy;

import Models.*;
import Models.Epsilon.Epsilon;
import View.Game.GameFrame;

import java.awt.*;

public class Enemy extends ObjectsInGame implements LocalRouting, Aggression, Moveable {

    private int collectibleNumber = 1;
    private int xpForEachCollectible = 5;
    private int numSides;
    private int power = 6;
    Epsilon epsilon;

    private boolean hovering;

    public Enemy(int x, int y, int hp, int collectibleNumber, int xpForEachCollectible, int numSides, int power, boolean hovering, GameFrame frame) {
        super(x, y, hp, frame);
        this.power = power;
        this.numSides = numSides;
        this.collectibleNumber = collectibleNumber;
        this.xpForEachCollectible = xpForEachCollectible;
        this.hovering = hovering;
    }
    public void removeTheImpactOnTheFrame(){

    }
    public Point[] makePoint(){
        return null;
    }

    @Override
    public void aggression() {

    }
    public void specialPowers(Epsilon epsilon){

    }


    @Override
    public void localRouting(int xEpsilon, int yEpsilon) {

    }

    @Override
    public void move() {

    }
    public boolean reduceHp(int powerOfAttack, AttackType attackType, EntityType from){
        this.setHp(this.getHp() - powerOfAttack);
        if(Constant.isqPressed() && from.equals(EntityType.EPSILON) && Constant.levelOfDefend >= 3)
            epsilon.reduceHp(-3, AttackType.REDUCE_FOR_INCREASE, EntityType.NOF_FOUND);

        if(this.getHp() <= 0)
            return true;
        else
            return false;
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

    public void addX(int amountForAdd){
        this.setX(this.getX() + amountForAdd);
    }
    public void addY(int amountForAdd){
        this.setY(this.getY() + amountForAdd);
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
