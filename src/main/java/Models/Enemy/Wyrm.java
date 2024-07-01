package Models.Enemy;

import Models.Constant;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class Wyrm extends Enemy{

    ArrayList<Shot> shots = new ArrayList<>();
    boolean clockWise = false;
    private double angle = 0;
    private int radius = 300, xEpsilon, yEpsilon, xEpsilonFrame, yEpsilonFrame;
    private GameFrame epsilonFrame;
    private boolean movingTowardsEpsilon = true;
    public Wyrm(int x, int y, GameFrame frame) {
        super(x, y, 12, 2, 8, 0, 8, false, frame);
        this.setHeight(Constant.WIDTH_OF_WYRM);
        this.setWidth(Constant.HEIGHT_OF_WYRM);
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        setSize(Constant.WIDTH_OF_WYRM, Constant.HEIGHT_OF_WYRM);
        background = MyProjectData.getProjectData().getWyrm();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }

    @Override
    public void move() {
        int deltaX = xEpsilon + xEpsilonFrame - this.getX() - this.getCurrentFrame().getX();
        int deltaY = yEpsilon + yEpsilonFrame - this.getY() - this.getCurrentFrame().getY();
        if (movingTowardsEpsilon) {

            // حرکت به سمت Epsilon
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance > radius) {
                double directionX = deltaX / distance;
                double directionY = deltaY / distance;
                if(!epsilonFrame.equals(currentFrame))
                    this.currentFrame.setLocation((int) (currentFrame.getX() + directionX * Constant.SPEED_OF_WYRM), (int) (currentFrame.getY() + directionY * Constant.SPEED_OF_WYRM));
                else{
                    this.addX((int) (directionX * Constant.SPEED_OF_WYRM));
                    this.addY((int) (directionY * Constant.SPEED_OF_WYRM));
                }
            } else {
                movingTowardsEpsilon = false;
            }
        } else {
            // حرکت در یک دایره در اطراف Epsilon
            angle += clockWise ? 0.005 : -0.005;
            if(!epsilonFrame.equals(currentFrame))
                this.currentFrame.setLocation((int) (xEpsilon + xEpsilonFrame + radius * Math.cos(angle)), (int) (yEpsilonFrame + yEpsilon + radius * Math.sin(angle)));
            else{
                this.setX((int) (xEpsilon + radius * Math.cos(angle)));
                this.setY((int) (yEpsilon + radius * Math.sin(angle)));
            }

        }


    }

    @Override
    public void specialPowers(Epsilon epsilon) {
        this.xEpsilon = epsilon.getX();
        this.yEpsilon = epsilon.getY();
        xEpsilonFrame = epsilon.getCurrentFrame().getX();
        yEpsilonFrame = epsilon.getCurrentFrame().getY();
        epsilonFrame = epsilon.getCurrentFrame();
        if(Math.random() <0.005 && epsilonFrame.equals(currentFrame)){
            shotAShot(xEpsilon, yEpsilon);
        }
        moveShots();
    }
    private void shotAShot(int xEpsilon, int yEpsilon){
        Shot shot = new Shot(this.getX(), this.getY(), this.currentFrame, false);
        shot.setV(xEpsilon, yEpsilon);
        shots.add(shot);
        currentFrame.addToGamePanel(shot);
    }
    private void moveShots(){
        for(Shot shot : shots){
            shot.move();
        }
    }

    public boolean isClockWise() {
        return clockWise;
    }
    @Override
    public void removeTheImpactOnTheFrame() {
        for(Shot shot : shots)
            currentFrame.removeFromGamePanel(shot);
    }

    public void setClockWise(boolean clockWise) {
        this.clockWise = clockWise;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
