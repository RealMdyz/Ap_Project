package Models.Enemy;

import Models.*;
import MyProject.MyProjectData;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.awt.*;

public class Trigorath extends Enemy implements LocalRouting, Aggression, Moveable {
    Constant constant;


    public Trigorath(int x, int y) {
        super(x, y, 15, 2, 5, 3, 10);
        this.setHeight(Constant.getHeightOfTrighrath());
        this.setWidth(Constant.getWidthOfTrighrath());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
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

        double dis = Math.sqrt(dx * dx + dy * dy);
        // Calculate the angle in radians
        double angle = Math.atan2(dy, dx);

        // Adjust speed based on distance using a sigmoid function
        double maxSpeed = Constant.getSpeedOfTrighrath();
        double minSpeed = maxSpeed / 2; // Adjust this value as needed
        double epsilonThreshold = 100; // Adjust this value as needed

        double sigmoidArg = (dis - epsilonThreshold) / (epsilonThreshold / 3); // Adjust this value as needed for the steepness of the sigmoid
        double speed = minSpeed + (maxSpeed - minSpeed) * (1 / (1 + Math.exp(-sigmoidArg)));

        // Convert angle to velocity components
        double vx = speed * Math.cos(angle);
        double vy = speed * Math.sin(angle);

        // Set the velocity based on the direction
        setxVelocity((int) vx);
        setyVelocity((int) vy);
    }



    @Override
    public void aggression() {
        // I do that in local routing !!!!
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfTrighrath(), Constant.getHeightOfTrighrath(), null);
    }
    @Override
    public void move(int xLimit, int yLimit) {
        // Calculate time difference and quadratic effect on movement
        double t = ((double)System.currentTimeMillis() - (double)this.getImpactTime()) / 1000;
        t = Math.max(0, 1 - t);

        boolean check = false;
        // Calculate movement impact based on time and velocity
        double x = this.getxVelocityImpact() * (t * t);
        double y = this.getyVelocityImpact() * (t * t);

        // Save current velocity for later restoration
        int vx = this.getxVelocity();
        int vy = this.getyVelocity();

        // Apply movement based on time effect if within valid range
        if (t != 0) {
            this.setxVelocity((int) x);
            this.setyVelocity((int) y);
        }


        if (this.getX() <= 0 && this.getxVelocity() > 0) {
            addX(this.getxVelocity());
        }
        else if (this.getX() >= xLimit - this.getWidth() - 10 && this.getxVelocity() < 0) {
            addX(this.getxVelocity());
        }
        else if (this.getX() >= 0 && this.getX() <= xLimit - this.getWidth() - 10) {
            addX(this.getxVelocity());
        }
        if (this.getY() <= 0 && this.getyVelocity() > 0) {
            addY(this.getyVelocity());
        }
        else if (this.getY() >= yLimit - this.getHeight() - 10 && this.getyVelocity() < 0) {
            addY(this.getyVelocity());
        }
        else if (this.getY() >= 0 && this.getY() <= yLimit - this.getHeight() - 10) {
            addY(this.getyVelocity());
        }


        // Restore original velocity if time effect is still active
        if (t != 0) {
            this.setxVelocity(vx);
            this.setyVelocity(vy);
        }
        if(!check)
            setAngleForRotate(0);
        if(getAngleForRotate() > 0){
            setAngleForRotate(getAngleForRotate() - 1);
            this.rotateImage(getAngleForRotate());
        }
    }




}
