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
    public void doImpact(int xImpact, int yImpact){
        int xChar = getX(); // Your character's x position
        int yChar = getY(); // Your character's y position

        // Calculate the direction vector from character to impact point
        double dx = xImpact - xChar;
        double dy = yImpact - yChar;

        // Calculate the distance from character to impact point
        double distance = Math.sqrt(dx * dx + dy * dy);
        double angle = Math.atan2(dy, dx);

        // Adjust speed as needed
        double speed = Constant.getSpeedOfImpact();
        System.out.println(xImpact + " " + yImpact);
        // Calculate the velocity components based on the distance
        double vx = speed * Math.cos(angle) ;
        double vy = speed * Math.sin(angle) ;
        this.setX(this.getX() + (int)(vx));
        this.setY(this.getY() + (int)(vy));
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
