package Models.BossFight;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.Random;

public class SmileyRightHand extends Enemy {
    Epsilon epsilon;
    public SmileyRightHand(int x, int y, GameFrame frame, Epsilon epsilon) {
        super(x, y, 100, 0, 0, 0, 25, false, frame);
        this.setHeight(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setWidth(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.epsilon = epsilon;
        this.setVisible(true);
        setSize(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        background = MyProjectData.getProjectData().getSmileyRightHand();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, null);

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
    public void setRandomPosAfterGettingAttack(){
        this.setX(Math.abs(new Random().nextInt(this.getCurrentFrame().getWidth() - this.getWidth())));
        this.setY(Math.abs(new Random().nextInt(this.getCurrentFrame().getHeight() - this.getHeight())));
    }
}
