package Models.Epsilon;

import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Vertex extends ObjectsInGame implements Moveable {
    private int x;
    private int y;
    private Color color;
    private double xFake, yFake;

    public Vertex(int x, int y) {
        super(x + 28, y + 28, 1);
        this.x = x + 28;
        this.y = y + 28;
        xFake = this.getX();
        yFake = this.getY();
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
        if(this.getX() <= 0  && this.getxVelocity() > 0){
            xFake += (this.getxVelocity());
            //     this.setX(this.getX() + this.getxVelocity());
            this.setX((int)xFake);
        }
        else if(this.getX() >= xLimit - this.getWidth() - 20 && this.getxVelocity() < 0){
            xFake += (this.getxVelocity());
            //     this.setX(this.getX() + this.getxVelocity());
            this.setX((int)xFake);
        }
        else if(this.getX() >= 0 && this.getX() <= xLimit - this.getWidth() - 20){
            xFake += (this.getxVelocity());
            //    this.setX(this.getX() + this.getxVelocity());
            this.setX((int)xFake);
        }
        if(this.getY() <= 0  && this.getyVelocity() > 0){
            yFake += this.getyVelocity();
            //    this.setY(this.getY() + this.getyVelocity());
            this.setY((int)yFake);
        }
        else if(this.getY() >= yLimit - this.getHeight() && this.getyVelocity() < 0){
            yFake += this.getyVelocity();
            //     this.setY(this.getY() + this.getyVelocity());
            this.setY((int)yFake);
        }
        else if(this.getY() >= 0 && this.getY() <= yLimit - this.getHeight()){
            yFake += this.getyVelocity();
       //     this.setY(this.getY() + this.getyVelocity());
            this.setY((int)yFake);
        }


    }

    public double getxFake() {
        return xFake;
    }

    public void setxFake(double xFake) {
        this.xFake = xFake;
        setX((int)xFake);
    }

    public double getyFake() {
        return yFake;
    }

    public void setyFake(double yFake) {
        this.yFake = yFake;
        setY((int)yFake);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.xFake = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.yFake = y;
    }
}

