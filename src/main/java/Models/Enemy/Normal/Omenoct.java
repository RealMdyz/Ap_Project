package Models.Enemy.Normal;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Epsilon.Vertex;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Omenoct extends Enemy {

    public ArrayList<Shot> shots = new ArrayList<>();
    public long lastShotTime = 0;
    private int side;
    private int targetX, targetY;
    private int tolerance = 2;
    private Epsilon epsilon;
    private ArrayList<Vertex> vertices;
    public Omenoct(int x, int y, int side, GameFrame frame) {
        super(x, y, 20, 8, 4, 8, 4, false, frame);
        this.side = side;
        this.setHeight(Constant.getHeightOfOmenoct());
        this.setWidth(Constant.getWidthOfOmenoct());
        setSize(Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct());
        background = MyProjectData.getProjectData().getOmenoct();
        setTargetPosition();
        addVertices();
    }
    private void addVertices() {
        vertices = new ArrayList<>();
        int centerX = this.getX() + this.getWidth() / 2;
        int centerY = this.getY() + this.getHeight() / 2;
        int radius = Math.min(this.getWidth(), this.getHeight()) / 2;

        for (int i = 0; i < 2; i++) {
            double angle = Math.toRadians((360.0 / 2) * i);
            int vertexX = (int) (centerX + radius * Math.cos(angle));
            int vertexY = (int) (centerY + radius * Math.sin(angle));
            Vertex vertex = new Vertex(vertexX - 7, vertexY - 7, 8, this.currentFrame);
            vertices.add(vertex);
            currentFrame.addToGamePanel(vertex); // Add vertex to the game panel
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }

    @Override
    public void move() {
        if(currentFrame.equals(epsilon.getCurrentFrame())){
            if(isInSide()){
                addX(0);
                addX(0);
            }
            else{
                int deltaX = targetX - this.getX();
                int deltaY = targetY - this.getY();
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (distance > 1) {
                    double directionX = deltaX / distance;
                    double directionY = deltaY / distance;
                    this.addX((int) (directionX * Constant.SPEED_OF_NORMAL_ENEMY));
                    this.addY((int) (directionY * Constant.SPEED_OF_NORMAL_ENEMY));
                }
                ///System.out.println(this.getX() + " " + this.getY() + " " +targetX + "  " + targetY + " " + deltaX + " " + deltaY + " " + side);
            }
        }
        else{

        }
        repaint();
    }

    @Override
    public void specialPowers(Epsilon epsilon) {
        this.epsilon = epsilon;
        int xEpsilon = epsilon.getX();
        int yEpsilon = epsilon.getY();
        shotAShot(xEpsilon, yEpsilon);
        moveShots();

    }
    private void shotAShot(int xEpsilon, int yEpsilon){
        if(System.currentTimeMillis() - lastShotTime > Constant.getEveryShotOmenoct()){
            Shot shot = new Shot(this.getX(), this.getY(), this.currentFrame, false);
            shot.setV(xEpsilon, yEpsilon);
            shots.add(shot);
            currentFrame.addToGamePanel(shot);
            lastShotTime = System.currentTimeMillis();
        }
    }
    private void moveShots(){
        for(Shot shot : shots){
            shot.move();
        }
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }
    @Override
    public void removeTheImpactOnTheFrame() {
        for(Shot shot : shots)
            currentFrame.removeFromGamePanel(shot);
        for(Vertex vertex : vertices)
            vertex.getCurrentFrame().removeFromGamePanel(vertex);
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
    public boolean isInSide() {
        switch (side) {
            case 0:
                return (getY() >= -tolerance && getY() <= tolerance);
            case 1:
                return (getY() >= currentFrame.getHeight() - this.getHeight() - tolerance && getY() <= currentFrame.getHeight() - this.getHeight() + tolerance);
            case 2:
                return getX() >= -tolerance && getX() <= tolerance;
            case 3:
                return getX() >=  currentFrame.getWidth() - this.getWidth() - tolerance && getX() <=  currentFrame.getWidth() - this.getWidth() + tolerance;
        }
        return false;
    }
    public void changeSide(){
        int l = Math.abs(new Random().nextInt() % 4);
        l = (l == 0 ? 1 : l);
        side = (side + l) % 4;
        setTargetPosition();
    }
    private void setTargetPosition() {
        switch (side) {
            case 0: // بالا
                targetX = this.getX();
                targetY = 0;
                break;
            case 1: // پایین
                targetX = this.getX();
                targetY = currentFrame.getHeight() - this.getHeight();
                break;
            case 2: // چپ
                targetX = 0;
                targetY = this.getY();
                break;
            case 3: // راست
                targetX = currentFrame.getWidth() - this.getWidth();
                targetY = this.getY();
                break;
        }
    }

    @Override
    public void addX(int amount){
        for(Vertex vertex : vertices)
            vertex.setX(vertex.getX() + amount);
        this.setX(this.getX() + amount);
    }
    @Override
    public void addY(int amount){
        for(Vertex vertex : vertices)
            vertex.setY(vertex.getY() + amount);
        this.setY(this.getY() + amount);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
}
