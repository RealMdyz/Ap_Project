package Models.Enemy;

import Models.*;
import MyProject.MyProjectData;

import java.awt.*;

public class Squarantine extends Enemy implements LocalRouting, Aggression, Moveable {
    Constant constant;

    public Squarantine(int x, int y) {
        super(x, y, 10, 1, 5, 4);
        this.setHeight(Constant.getHeightOfSquarantine());
        this.setWidth(Constant.getWidthOfSquarantine());
        setSize(Constant.getWidthOfSquarantine(), Constant.getHeightOfSquarantine());
        background = MyProjectData.getProjectData().getSquarAntine();
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
        double speed = Constant.getSpeedOfSquarantine(); // Adjust speed as needed
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
        g2D.drawImage(background, 0, 0, Constant.getWidthOfSquarantine(), Constant.getHeightOfSquarantine(), null);

    }
    @Override
    public void move(int xLimit, int yLimit) {
        this.setxVelocity(this.getxVelocity() + this.getxVelocityImpact());
        this.setyVelocity(this.getyVelocity() + this.getyVelocityImpact());

        //System.out.println(this.getxVelocityImpact() + " " + this.getyVelocityImpact());
        if(this.getX() <= 0  && this.getxVelocity() > 0)
            this.setX(this.getX() + this.getxVelocity());
        else if(this.getX() >= xLimit - this.getWidth() - 10 && this.getxVelocity() < 0)
            this.setX(this.getX() + this.getxVelocity());
        else if(this.getX() >= 0 && this.getX() <= xLimit - this.getWidth() - 10)
            this.setX(this.getX() + this.getxVelocity());
        if(this.getY() <= 0  && this.getyVelocity() > 0)
            this.setY(this.getY() + this.getyVelocity());
        else if(this.getY() >= yLimit - this.getHeight() - 10 && this.getyVelocity() < 0)
            this.setY(this.getY() + this.getyVelocity());
        else if(this.getY() >= 0 && this.getY() <= yLimit - this.getHeight() - 10)
            this.setY(this.getY() + this.getyVelocity());;

        this.setxVelocity(this.getxVelocity() - this.getxVelocityImpact());
        this.setyVelocity(this.getyVelocity() - this.getyVelocityImpact());
        this.setxVelocityImpact(Math.max(this.getxVelocityImpact() - 1, 0));
        this.setyVelocityImpact(Math.max(this.getyVelocityImpact() - 1, 0));

    }

}
