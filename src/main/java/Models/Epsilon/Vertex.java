package Models.Epsilon;

import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Vertex extends ObjectsInGame implements Moveable {
    private int x;
    private int y;
    private Color color;

    public Vertex(int x, int y) {
        super(x + 28, y + 28, 1);
        this.x = x;
        this.y = y;
        this.setHeight(15);
        this.setWidth(15);
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        setSize(this.getWidth(), this.getHeight());
        this.color = Color.RED;
        background = MyProjectData.getProjectData().getVertexOnEpsilon();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
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

