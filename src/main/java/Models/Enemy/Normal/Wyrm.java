package Models.Enemy.Normal;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Side;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class Wyrm extends Enemy {

    ArrayList<Shot> shots = new ArrayList<>();
    boolean clockWise = false;
    private double angle = 0;
    private int radius = Constant.NON_HOVERING_DISTANCE, xEpsilon, yEpsilon, xEpsilonFrame, yEpsilonFrame;
    private GameFrame epsilonFrame;
    private boolean movingTowardsEpsilon = true;
    Epsilon epsilon;
    public Wyrm(int x, int y, GameFrame frame) {
        super(x, y, 12, 2, 8, 0, 8, false, frame);
        this.setVisible(true);
        this.setHeight(Constant.WIDTH_OF_WYRM);
        this.setWidth(Constant.HEIGHT_OF_WYRM);
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
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (movingTowardsEpsilon) {

            // حرکت به سمت Epsilon


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
        } else if(distance <= radius){
            // حرکت در یک دایره در اطراف Epsilon
            angle += clockWise ? 0.05 : -0.05;
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
        this.epsilon = epsilon;
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
        Shot shot = new Shot(this.getX(), this.getY(), this.getPower(), this.currentFrame, false);
        shot.setV(xEpsilon, yEpsilon);
        shots.add(shot);
        currentFrame.addToGamePanel(shot);
    }

    private void moveShots(){
        for(Shot shot : shots){
            shot.move();
        }
    }
    public void changingClockwiseOrCounterClockwise(){
        if(clockWise)
            clockWise = false;
        else
            clockWise = true;
    }

    public boolean isClockWise() {
        return clockWise;
    }
    @Override
    public void removeTheImpactOnTheFrame() {
        for(Shot shot : shots)
            currentFrame.removeFromGamePanel(shot);
    }
    public boolean reduceHp(int powerOfAttack, AttackType attackType, EntityType from){
        this.setHp(this.getHp() - powerOfAttack);
        if(Constant.isqPressed() && from.equals(EntityType.EPSILON) && Constant.levelOfDefend >= 3)
            epsilon.reduceHp(-3, AttackType.REDUCE_FOR_INCREASE, EntityType.NOF_FOUND);
        if(this.getHp() <= 0)
            return true;
        else
            return false;
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

    public boolean isMovingTowardsEpsilon() {
        return movingTowardsEpsilon;
    }

    public void setMovingTowardsEpsilon(boolean movingTowardsEpsilon) {
        this.movingTowardsEpsilon = movingTowardsEpsilon;
    }
}
