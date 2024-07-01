package Models.Enemy;

import Controller.Game.Intersection;
import Models.Constant;
import Models.Epsilon.Epsilon;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Archmire extends Enemy{


    private List<Aoe> aoeList;
    private int aoeDuration = 5000; // 5 seconds
    boolean mini;
    int aoePower, drownPower;
    long lastAoeTime, lastDrownAttack = 0;
    int xEpsilon, xEpsilonFrame, yEpsilon, yEpsilonFrame;



    public Archmire(int x, int y, boolean isMini, GameFrame frame) {
        super(x, y, 30, isMini? 2 : 5, isMini ? 3 : 6, 0, 0, true, frame);
        lastAoeTime = System.currentTimeMillis();
        aoePower = 2;
        drownPower = 10;
        this.setHeight(Constant.getHeightOfArchmire());
        this.setWidth(Constant.getWidthOfArchmire());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        this.setVisible(true);
        setSize(Constant.getHeightOfArchmire(), Constant.getWidthOfArchmire());
        background = MyProjectData.getProjectData().getArchmire();
        aoeList = new ArrayList<>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick(), null);

        for (Aoe aoe : aoeList) {
            g2D.setColor(new Color(255, 0, 0, 100)); // Semi-transparent red
            g2D.fillRect(aoe.getX(), aoe.getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void specialPowers(Epsilon epsilon) {
        xEpsilon = epsilon.getX();
        xEpsilonFrame = epsilon.getCurrentFrame().getX();
        yEpsilon = epsilon.getY();
        yEpsilonFrame = epsilon.getCurrentFrame().getY();
        checkAoEDamage(epsilon);
    }

    public void addAoE(int x, int y) {
        Aoe aoe = new Aoe(x, y ,aoeDuration, getWidth(), getHeight());
        aoeList.add(aoe);
    }
    private void updateAoE(long deltaTime) {
        Iterator<Aoe> iterator = aoeList.iterator();
        while (iterator.hasNext()) {
            Aoe aoe = iterator.next();
            if (aoe.isExpired()) {
                iterator.remove();
            }
        }
    }
    public void checkAoEDamage(Epsilon epsilon) {
        for (Aoe aoe : aoeList) {
            if (Intersection.isInAoE(epsilon, aoe) && System.currentTimeMillis() - aoe.getLastAttackFromMe() > 1000) {
                epsilon.setHp(epsilon.getHp() - aoePower);
                aoe.setLastAttackFromMe(System.currentTimeMillis());
            }
        }
    }
    @Override
    public void move() {
        int deltaX = xEpsilon + xEpsilonFrame - this.getX() - this.getCurrentFrame().getX();
        int deltaY = yEpsilon + yEpsilonFrame - this.getY() - this.getCurrentFrame().getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double directionX = deltaX / distance;
        double directionY = deltaY / distance;

        addX((int)(directionX * Constant.getSpeedOfArchmire()));
        addY((int)(directionY * Constant.getSpeedOfArchmire()));

        if(Math.random() < 0.01){
            addAoE(this.getX(), this.getY());
            repaint();
        }
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
}
