package Models.Enemy;

import Models.*;

public class Enemy extends ObjectsInGame implements LocalRouting, Aggression, Moveable {

    private int collectibleNumber = 1;
    private int xpForEachCollectible = 5;
    private int numSides;


    public Enemy(int x, int y, int hp, int collectibleNumber, int xpForEachCollectible, int numSides) {
        super(x, y, hp);
        this.numSides = numSides;
        this.collectibleNumber = collectibleNumber;
        this.xpForEachCollectible = xpForEachCollectible;
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


}
