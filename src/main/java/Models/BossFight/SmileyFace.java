package Models.BossFight;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class SmileyFace extends Enemy {

    public boolean isInEpsilonFrameForProjectile = false;
    public double angle = 0;
    public int radius = 150;

    public SmileyFace(int x, int y, GameFrame frame) {
        super(x, y, 300, 0, 0, 0, 0, false, frame);
        this.setHeight(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setWidth(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setVisible(true);
        setSize(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        background = MyProjectData.getProjectData().getSmileyFace();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, null);
    }
    public void move(Epsilon epsilon){
        if(isInEpsilonFrameForProjectile){
            angle += 0.01;
            this.setX((int) (epsilon.getX() + radius * Math.cos(angle)));
            this.setY((int) (epsilon.getY() + radius * Math.sin(angle)));
        }
    }

    public boolean isInEpsilonFrameForProjectile() {
        return isInEpsilonFrameForProjectile;
    }

    public void setInEpsilonFrameForProjectile(boolean inEpsilonFrameForProjectile) {
        isInEpsilonFrameForProjectile = inEpsilonFrameForProjectile;
    }
}
