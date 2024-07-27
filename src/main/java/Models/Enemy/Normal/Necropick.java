package Models.Enemy.Normal;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Aoe;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class Necropick extends Enemy {

    long time = System.currentTimeMillis();
    private ArrayList<Shot> shots = new ArrayList<>();
    private boolean setVisibilityAtThisTimeOfAppearing = false;
    private boolean lastSettingThePos = false;
    private Aoe markArea;
    int[] x = {-50, -50, 50, 50};
    int[] y = {50, -50, 50, -50};
    Epsilon epsilon;

    boolean canShot = true;
    public Necropick(int x, int y, GameFrame frame) {
        super(x, y, 10, 4, 2, 0, 5, false, frame);
        this.setHeight(Constant.getHeightOfNecropick());
        this.setWidth(Constant.getWidthOfNecropick());
        this.setVisible(false);
        markArea = new Aoe(0, 0, 2000, 0, 10, 10, frame);
        setSize(Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick());
        background = MyProjectData.getProjectData().getNecropick();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }


    @Override
    public void move() {
        // EndPoint =
        addX(0);
        addY(0);
    }
    @Override
    public void specialPowers(Epsilon epsilon) {
        this.epsilon = epsilon;
        int xEpsilon = epsilon.getX();
        int yEpsilon = epsilon.getY();
        checkTimeForAppearAndDisappear(xEpsilon, yEpsilon);
        moveShots();
    }
    private void checkTimeForAppearAndDisappear(int xEpsilon, int yEpsilon){
        /*if(!currentFrame.equals(epsilon.getCurrentFrame()))
            this.changeFrameAndPaint(epsilon.getCurrentFrame());*/
        long current = System.currentTimeMillis();
        if(current - time < 7500){
            this.setVisible(false);
            this.setHovering(true);
            setVisibilityAtThisTimeOfAppearing = false;
            lastSettingThePos = false;
        }
        else if(current - time < 8000){
            if(!lastSettingThePos){
                Point newLocation = getRandomPointInCircle(Constant.RADIUS_FOR_APPEARING_NECROPICK, xEpsilon, yEpsilon); // شعاع دلخواه را جایگزین کنید
                this.setX(newLocation.x);
                this.setY(newLocation.y);
                markArea = new Aoe(this.getX(), this.getY(), 2000, 0, 15, 15, currentFrame);
                System.out.println(markArea.getX() + " " + markArea.getY());
                markArea.getCurrentFrame().addToGamePanel(markArea);
                lastSettingThePos = true;
            }
        }
        else if(System.currentTimeMillis() - time < 12000){
            this.setVisible(true);
            if(!setVisibilityAtThisTimeOfAppearing){
                setVisibilityAtThisTimeOfAppearing = true;
                markArea.getCurrentFrame().removeFromGamePanel(markArea);
                this.setHovering(false);
                fireShots();
            }
        }
        else{
            time = System.currentTimeMillis();
        }
    }

    @Override
    public void removeTheImpactOnTheFrame() {
        for(Shot shot : shots)
            currentFrame.removeFromGamePanel(shot);
        if(isHovering())
             markArea.getCurrentFrame().removeFromGamePanel(markArea);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }
    private Point getRandomPointInCircle(int radius, int xCenter, int yCenter) {
        double angle = Math.random() * 2 * Math.PI;
        double r = radius * Math.sqrt(Math.random());
        if(epsilon.getEpsilonLogic().isInDeimosDismay())
            r = Constant.NON_HOVERING_DISTANCE;
        int x = (int) (xCenter + r * Math.cos(angle));
        int y = (int) (yCenter + r * Math.sin(angle));
        return new Point(x, y);
    }
    private void fireShots() {
        shots.clear();
        shots = new ArrayList<>();
        int shotCount = 8;
        double angleStep = 2 * Math.PI / shotCount;
        for (int i = 0; i < shotCount; i++) {
            double angle = i * angleStep;
            int xVelocity = (int) (Math.cos(angle) * Constant.getSpeedOfShot());
            int yVelocity = (int) (Math.sin(angle) * Constant.getSpeedOfShot());
            Shot shot = new Shot(this.getX(), this.getY(), this.getPower(), this.currentFrame, true); // true به معنای صلب بودن تیر است
            shot.setxVelocity(xVelocity);
            shot.setyVelocity(yVelocity);
            shots.add(shot);
            this.currentFrame.add(shot); // اضافه کردن تیر به فریم
        }
    }
    private void moveShots(){
        for(Shot shot : shots){
            shot.move();
        }
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

}
