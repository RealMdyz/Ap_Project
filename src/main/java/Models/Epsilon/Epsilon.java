package Models.Epsilon;

import Models.Constant;
import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Epsilon extends ObjectsInGame implements Moveable {

    public static int levelOfWritOfAres = 0;
    public static int levelOfWritOfProteus = 1;
    public static boolean writeOfAceso = false;
    private long prevAceso = 0;
    private ArrayList<Vertex> vertices = new ArrayList<>();

    private long startOfAthena = 0;
    private int radius = 35;

    private int count = 0;

    public Epsilon(int x, int y) {
        super(x, y, 100);
        this.setHeight(70);
        this.setWidth(70);
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
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

      //  System.out.println(getxCenter() + " " + getyCenter());
        double t = ((double)System.currentTimeMillis() - (double)this.getImpactTime()) / 1000;
        t = Math.max(0, (1 - t));
        double x = (this.getxVelocityImpact()) * (t);
        double y = ((this.getyVelocityImpact())) * (t);

        this.setxVelocity(this.getxVelocity() + (int)x);
        this.setyVelocity(this.getyVelocity() + (int)y);

        for(Vertex vertex : vertices){
            vertex.setxVelocity(this.getxVelocity());
            vertex.setyVelocity(this.getyVelocity());
            vertex.move(xLimit, yLimit);
        }

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
        //System.out.println(x + " " + y);
        this.setxVelocity(this.getxVelocity() - (int)x);
        this.setyVelocity(this.getyVelocity() - (int)y);

    }
    public void changeSize(int newWidth, int newHeight) {
        this.setWidth(newWidth);
        this.setHeight(newHeight);
        setSize(newWidth, newHeight);
        repaint(); // Call repaint to ensure the changes are reflected visually
    }
    public void doImpactToWall(int xLimit, int yLimit){
        if(this.getX() < 0 || this.getY() < 0){
            double angle = Math.atan2(this.getX(), this.getY());
            double vx = Math.cos(angle) * Constant.getSpeedOfImpact();
            double vy = Math.sin(angle) * Constant.getSpeedOfImpact();
            this.setImpactTime(System.currentTimeMillis());
            setxVelocityImpact(vx);
            setyVelocityImpact(vy);
        }
        else if(this.getX() + this.getWidth() + 20 >= xLimit || this.getY() + this.getHeight() + 20 >= yLimit){
            double angle = Math.atan2(- this.getY(), - this.getX());
            double vx = Math.cos(angle) * Constant.getSpeedOfImpact();
            double vy = Math.sin(angle) * Constant.getSpeedOfImpact();
            this.setImpactTime(System.currentTimeMillis());
            setxVelocityImpact(vx);
            setyVelocityImpact(vy);
        }
    }
    public void addVertex(){
        //System.out.println(levelOfWritOfProteus);
        double angleBetweenVertices = 2 * Math.PI / levelOfWritOfProteus;
        for (int i = 0; i < levelOfWritOfProteus; i++) {
            double angle = i * angleBetweenVertices;
            int vx = (int) (getX() + radius * Math.cos(angle));
            int vy = (int) (getY() + radius * Math.sin(angle));
            vertices.add(new Vertex( vx, vy));
        }
    }
    public void rotateVertices(double angle) {
        // Use the provided angle directly in radians for the rotation
        double centerX = this.getxCenter();
        double centerY = this.getyCenter();

        // Iterate through each vertex
        if(!Constant.isRotateVertices()){
            angle = 0;
        }

        for (Vertex vertex : vertices) {
            // Calculate relative position from the center
            double relativeX = vertex.getX() - centerX;
            double relativeY = vertex.getY() - centerY;

            // Apply the rotation transformation
            double rotatedX = relativeX * Math.cos(angle) - relativeY * Math.sin(angle);
            double rotatedY = relativeX * Math.sin(angle) + relativeY * Math.cos(angle);

            // Calculate the new absolute position of the vertex
            double newX = rotatedX + centerX;
            double newY = rotatedY + centerY;

            // Set the new positions of the vertex
            vertex.setxFake(newX);
            vertex.setyFake(newY);
        }
        count += 1;
        if(count > 10  && Constant.isRotateVertices()){
            count = 0;
            setPosVertex();
        }
    }
    public void setPosVertex(){
        double angleBetweenVertices = 2 * Math.PI / levelOfWritOfProteus;
        int index = 0;
        for (Vertex vertex : vertices) {
            double angle = index * angleBetweenVertices;
            int vx = (int) (getX() + radius * Math.cos(angle));
            int vy = (int) (getY() + radius * Math.sin(angle));
            vertex.setX(vx);
            vertex.setY(vy);
            index += 1;
        }

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
        return radius + 3;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void clearVertices() {
        vertices.clear();
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
}
