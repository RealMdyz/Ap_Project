package Models.BossFight;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;

public class SmileyLeftHand extends Enemy {

    Epsilon epsilon;
    public SmileyLeftHand(int x, int y, GameFrame frame, Epsilon epsilon) {
        super(x, y, 100, 0, 0, 0, 0, false, frame);
        this.setHeight(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setWidth(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setVisible(true);
        this.epsilon = epsilon;
        setSize(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        background = MyProjectData.getProjectData().getSmileyLeftHand();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, null);

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
