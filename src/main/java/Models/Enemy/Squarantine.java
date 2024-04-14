package Models.Enemy;

import Models.*;
import MyProject.MyProjectData;

import java.awt.*;

public class Squarantine extends Enemy implements LocalRouting, Aggression, Moveable {
    Constant constant;

    public Squarantine(int x, int y) {
        super(x, y, 10, 1, 5, 4, 6);
        this.setHeight(Constant.getHeightOfSquarantine());
        this.setWidth(Constant.getWidthOfSquarantine());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
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

        double t = ((double)System.currentTimeMillis() - (double)this.getImpactTime()) / 1000;
        t = Math.max(0, (1 - t));
        double x = (this.getxVelocityImpact()) * (t * t);
        double y = ((this.getyVelocityImpact())) * (t * t);
        int vx = this.getxVelocity();
        int vy = this.getyVelocity();
        if(t != 0){
            this.setxVelocity((int)x);
            this.setyVelocity((int)y);
        }

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
            this.setY(this.getY() + this.getyVelocity());

        if(t != 0){
            this.setxVelocity(vx);
            this.setyVelocity(vy);
        }
    }

}
