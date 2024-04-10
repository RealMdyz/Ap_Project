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


    public Epsilon(int x, int y) {
        super(x, y, 15);
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
    public void move(int xLimit, int yLimit) {
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
}
