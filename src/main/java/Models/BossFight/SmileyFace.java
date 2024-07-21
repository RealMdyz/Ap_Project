package Models.BossFight;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SmileyFace extends Enemy {

    public boolean isInEpsilonFrameForProjectile = false;
    public double angle = 0;
    public int radius = 150;
    Epsilon epsilon;
    ArrayList<Shot> rapidFireShots = new ArrayList<>();

    public SmileyFace(int x, int y, GameFrame frame, Epsilon epsilon) {
        super(x, y, 0, 0, 0, 0, 5, false, frame);
        this.epsilon = epsilon;
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
    public boolean reduceHp(int powerOfAttack, AttackType attackType, EntityType from){
        this.setHp(this.getHp() - powerOfAttack);
        this.setRandomPosAfterGettingAttack();
        if(Constant.isqPressed() && from.equals(EntityType.EPSILON) && Constant.levelOfDefend >= 3)
            epsilon.reduceHp(-3, AttackType.REDUCE_FOR_INCREASE, EntityType.NOF_FOUND);

        if(this.getHp() <= 0)
            return true;
        else
            return false;
    }
    private void setRandomPosAfterGettingAttack(){
        this.setX(Math.abs(new Random().nextInt(this.getCurrentFrame().getWidth() - this.getWidth())));
        this.setY(Math.abs(new Random().nextInt(this.getCurrentFrame().getHeight() - this.getHeight())));
    }

    public boolean isInEpsilonFrameForProjectile() {
        return isInEpsilonFrameForProjectile;
    }

    public void setInEpsilonFrameForProjectile(boolean inEpsilonFrameForProjectile) {
        isInEpsilonFrameForProjectile = inEpsilonFrameForProjectile;
    }

    public ArrayList<Shot> getRapidFireShots() {
        return rapidFireShots;
    }

    public void setRapidFireShots(ArrayList<Shot> rapidFireShots) {
        this.rapidFireShots = rapidFireShots;
    }
}
