package Models.Epsilon;

import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Epsilon extends ObjectsInGame implements Moveable {

    private int xVelocity = 0;
    private int yVelocity = 0;

    public Epsilon(int x, int y) {
        super(x, y);
        this.setHeight(70);
        this.setWidth(70);
        setSize(getWidth(), getHeight());
        background = MyProjectData.getProjectData().getEpsilonCircle();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, 70, 70, null);
        /*
        g2D.fill(new Arc2D.Double(getX() - radius, getY() - radius, 2 * radius, 2 * radius, 0, 360, Arc2D.PIE));
        g2D.dispose();
        */
    }

    @Override
    public void move() {
        this.setX(this.getX() + this.xVelocity);
        this.setY(this.getY() + this.yVelocity);
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
}
