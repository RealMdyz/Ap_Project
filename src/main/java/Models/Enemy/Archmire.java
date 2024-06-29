package Models.Enemy;

import Models.Constant;
import Models.Epsilon.Epsilon;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Archmire extends Enemy{

    boolean mini;
    int aoePower, drownPower;
    long aoeTime;
    int xEpsilon, xEpsilonFrame, yEpsilon, yEpsilonFrame;

    public Archmire(int x, int y, boolean isMini, GameFrame frame) {
        super(x, y, 30, isMini? 2 : 5, isMini ? 3 : 6, 0, 0, true, frame);
        aoeTime = 5000;
        aoePower = 2;
        drownPower = 10;
        this.setHeight(Constant.getHeightOfArchmire());
        this.setWidth(Constant.getWidthOfArchmire());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        this.setVisible(true);
        setSize(Constant.getHeightOfArchmire(), Constant.getWidthOfArchmire());
        background = MyProjectData.getProjectData().getArchmire();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick(), null);
    }

    @Override
    public void specialPowers(Epsilon epsilon) {
        xEpsilon = epsilon.getX();
        xEpsilonFrame = epsilon.getCurrentFrame().getX();
        yEpsilon = epsilon.getY();
        yEpsilonFrame = epsilon.getCurrentFrame().getY();

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

    public long getAoeTime() {
        return aoeTime;
    }

    public void setAoeTime(long aoeTime) {
        this.aoeTime = aoeTime;
    }
}
