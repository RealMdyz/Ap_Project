package Models.Epsilon;

import Models.Moveable;
import Models.ObjectsInGame;

import java.awt.*;

public class Vertex extends ObjectsInGame implements Moveable {
    private int x;
    private int y;
    private Color color; // رنگ راس

    public Vertex(int x, int y) {
        super(x, y, 0);
        this.x = x;
        this.y = y;
        this.color = Color.RED;
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public void move(int xLimit, int yLimit){
        if(this.getX() <= 0  && this.getxVelocity() > 0)
            this.setX(this.getX() + this.getxVelocity());
        else if(this.getX() >= xLimit - this.getWidth() - 10 && this.getxVelocity() < 0)
            this.setX(this.getX() + this.getxVelocity());
        else if(this.getX() >= 0 && this.getX() <= xLimit - this.getWidth() - 10)
            this.setX(this.getX() + this.getxVelocity());
        if(this.getY() <= 0  && this.getyVelocity() > 0)
            this.setY(this.getY() + this.getyVelocity());
        else if(this.getY() >= yLimit - this.getHeight() && this.getyVelocity() < 0)
            this.setY(this.getY() + this.getyVelocity());
        else if(this.getY() >= 0 && this.getY() <= yLimit - this.getHeight())
            this.setY(this.getY() + this.getyVelocity());
    }
}

