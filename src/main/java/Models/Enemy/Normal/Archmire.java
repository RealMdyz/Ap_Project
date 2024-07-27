package Models.Enemy.Normal;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Aoe;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Archmire extends Enemy {


    private List<Aoe> aoeList;
    private int aoeDuration = 5000; // 5 seconds
    boolean mini;
    int aoePower, drownPower;
    long lastAoeTime, lastDrownAttack = 0, lastSpawnAoe;
    public static final long TIME_AOE_LOOP = 400;
    int xEpsilon, xEpsilonFrame, yEpsilon, yEpsilonFrame;
    Epsilon epsilon;

    public Archmire(int x, int y, boolean isMini, GameFrame frame) {
        super(x, y, 30, isMini? 2 : 5, isMini ? 3 : 6, 0, 0, true, frame);
        lastAoeTime = System.currentTimeMillis();
        aoePower = 2;
        drownPower = 10;
        lastSpawnAoe = 0;
        this.setHeight(Constant.getHeightOfArchmire());
        this.setWidth(Constant.getWidthOfArchmire());
        this.setVisible(true);
        setSize(Constant.getHeightOfArchmire(), Constant.getWidthOfArchmire());
        background = MyProjectData.getProjectData().getArchmire();
        aoeList = new ArrayList<>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick(), null);
    }
    @Override
    public void specialPowers(Epsilon epsilon) {
        this.epsilon = epsilon;
        xEpsilon = epsilon.getX();
        xEpsilonFrame = epsilon.getCurrentFrame().getX();
        yEpsilon = epsilon.getY();
        yEpsilonFrame = epsilon.getCurrentFrame().getY();
        repaint();
    }
    public void addAoE(int x, int y) {
        Aoe aoe = new Aoe(x, y, aoeDuration, aoePower, getWidth(), getHeight(), currentFrame);
        aoeList.add(aoe);
        currentFrame.addToGamePanel(aoe);
        repaint();
    }

    public void checkAoEDamage(ObjectsInGame objectsInGame) {
        for (Aoe aoe : aoeList) {
            aoe.checkAndReduceTheHP(objectsInGame, EntityType.ENEMY);
        }
    }

    @Override
    public void removeTheImpactOnTheFrame() {
        for(Aoe aoe : aoeList)
            aoe.getCurrentFrame().removeFromGamePanel(aoe);
    }

    @Override
    public void move() {
        /*if(!currentFrame.equals(epsilon.getCurrentFrame()))
            this.changeFrameAndPaint(epsilon.getCurrentFrame());*/
        int deltaX = xEpsilon + xEpsilonFrame - this.getX() - this.getCurrentFrame().getX();
        int deltaY = yEpsilon + yEpsilonFrame - this.getY() - this.getCurrentFrame().getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double directionX = deltaX / distance;
        double directionY = deltaY / distance;
        if(distance > Constant.NON_HOVERING_DISTANCE || !epsilon.getEpsilonLogic().isInDeimosDismay()){
            addX((int)(directionX * Constant.getSpeedOfArchmire()));
            addY((int)(directionY * Constant.getSpeedOfArchmire()));
        }

        repaint();

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

    public boolean isMini() {
        return mini;
    }

    public void setMini(boolean mini) {
        this.mini = mini;
    }

    public int getAoePower() {
        return aoePower;
    }

    public void setAoePower(int aoePower) {
        this.aoePower = aoePower;
    }

    public int getDrownPower() {
        return drownPower;
    }

    public void setDrownPower(int drownPower) {
        this.drownPower = drownPower;
    }

    public long getLastAoeTime() {
        return lastAoeTime;
    }

    public void setLastAoeTime(long lastAoeTime) {
        this.lastAoeTime = lastAoeTime;
    }

    public long getLastDrownAttack() {
        return lastDrownAttack;
    }

    public void setLastDrownAttack(long lastDrownAttack) {
        this.lastDrownAttack = lastDrownAttack;
    }

    public List<Aoe> getAoeList() {
        return aoeList;
    }

    public void setAoeList(List<Aoe> aoeList) {
        this.aoeList = aoeList;
    }
}
