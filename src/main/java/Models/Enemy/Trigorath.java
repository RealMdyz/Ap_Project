package Models.Enemy;

import Models.*;
import MyProject.MyProjectData;

import java.awt.*;

public class Trigorath extends Enemy implements LocalRouting, Aggression, Moveable {
    Constant constant;
    private int collectibleNumber = 2;
    private int xpForEachCollectible = 5;

    public Trigorath(int x, int y) {
        super(x, y, 15);
        this.setHeight(Constant.getHeightOfTrighrath());
        this.setWidth(Constant.getWidthOfTrighrath());
        setSize(Constant.getWidthOfTrighrath(), Constant.getHeightOfTrighrath());
        background = MyProjectData.getProjectData().getTriGorath();
    }

    @Override
    public void localRouting(int xEpsilon, int yEpsilon) {
        int xChar = getX(); // Your character's x position
        int yChar = getY(); // Your character's y position

        // Calculate the direction vector from character to epsilon character
        double dx = xEpsilon - xChar;
        double dy = yEpsilon - yChar;

        // Calculate the angle in radians
        double angle = Math.atan2(dy, dx);

        // Convert angle to velocity components
        double speed = Constant.getSpeedOfTrighrath(); // Adjust speed as needed
        double vx = speed * Math.cos(angle);
        double vy = speed * Math.sin(angle);

        // Set the velocity based on the direction (adjust speed according to your game's requirement)// Adjust speed as needed
        setxVelocity((int) (vx));
        setyVelocity((int) (vy));
    }

    @Override
    public void aggression() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfTrighrath(), Constant.getHeightOfTrighrath(), null);
        /*
        g2D.fill(new Arc2D.Double(getX() - radius, getY() - radius, 2 * radius, 2 * radius, 0, 360, Arc2D.PIE));
        g2D.dispose();
        */
    }
    @Override
    public void move() {
        setX(this.getX() + this.getxVelocity());
        setY(this.getY() + this.getyVelocity());
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
}
