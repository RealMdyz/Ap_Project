package Models.Epsilon;

import Models.Constant;
import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Epsilon extends ObjectsInGame implements Moveable {

    public Epsilon(int x, int y) {
        super(x, y, 100);
        this.setHeight(70);
        this.setWidth(70);
        setSize(70, 70);
        background = MyProjectData.getProjectData().getEpsilonCircle();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, 70, 70, null);
    }

    @Override
    public void move() {
        this.setX(this.getX() + this.getxVelocity());
        this.setY(this.getY() + this.getyVelocity());
    }
    public void doImpact(int xImpact, int yImpact){
        int xChar = getX(); // Your character's x position
        int yChar = getY(); // Your character's y position

        // Calculate the direction vector from character to impact point
        double dx = xImpact - xChar;
        double dy = yImpact - yChar;

        // Calculate the distance from character to impact point
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Adjust speed as needed
        double speed = Constant.getSpeedOfSquarantine();

        // Calculate the velocity components based on the distance
        double vx = speed * dx / distance;
        double vy = speed * dy / distance;
        this.setX(this.getX() + (int)(vx));
        this.setY(this.getY() + (int)(vy));
    }


}
