package Models.Epsilon;

import Models.Constant;
import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Epsilon extends ObjectsInGame implements Moveable {

    public static int levelOfWritOfAres = 0;
    public static int levelOfWritOfProteus = 0;
    public static boolean writeOfAceso = false;
    private long prevAceso = 0;

    private long startOfAthena = 0;
    private int radius = 35;

    public Epsilon(int x, int y) {
        super(x, y, 100);
        this.setHeight(70);
        this.setWidth(70);
        setSize(this.getWidth(), this.getHeight());
        background = MyProjectData.getProjectData().getEpsilonCircle();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void move(int xLimit, int yLimit) {
        this.setxVelocity(this.getxVelocity() + this.getxVelocityImpact());
        this.setyVelocity(this.getyVelocity() + this.getyVelocityImpact());

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

        this.setxVelocity(this.getxVelocity() - this.getxVelocityImpact());
        this.setyVelocity(this.getyVelocity() - this.getyVelocityImpact());
        this.setxVelocityImpact(Math.max(this.getxVelocityImpact() - 1, 0));
        this.setyVelocityImpact(Math.max(this.getyVelocityImpact() - 1, 0));
    }
    public void changeSize(int newWidth, int newHeight) {
        this.setWidth(newWidth);
        this.setHeight(newHeight);
        setSize(newWidth, newHeight);
        repaint(); // Call repaint to ensure the changes are reflected visually
    }

    public static int getLevelOfWritOfAres() {
        return levelOfWritOfAres;
    }

    public static void setLevelOfWritOfAres(int levelOfWritOfAres) {
        Epsilon.levelOfWritOfAres = levelOfWritOfAres;
    }

    public static int getLevelOfWritOfProteus() {
        return levelOfWritOfProteus;
    }

    public static void setLevelOfWritOfProteus(int levelOfWritOfProteus) {
        Epsilon.levelOfWritOfProteus = levelOfWritOfProteus;
    }

    public static boolean isWriteOfAceso() {
        return writeOfAceso;
    }

    public static void setWriteOfAceso(boolean writeOfAceso) {
        Epsilon.writeOfAceso = writeOfAceso;
    }

    public long getPrevAceso() {
        return prevAceso;
    }

    public void setPrevAceso(long prevAceso) {
        this.prevAceso = prevAceso;
    }

    public long getStartOfAthena() {
        return startOfAthena;
    }

    public void setStartOfAthena(long startOfAthena) {
        this.startOfAthena = startOfAthena;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
