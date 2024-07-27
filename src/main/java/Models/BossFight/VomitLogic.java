package Models.BossFight;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.Enemy.Aoe;
import Models.EntityType;
import Models.Epsilon.Epsilon;

import java.util.ArrayList;
import java.util.Random;

public class VomitLogic {
    protected void doAVomit(ArrayList<Aoe> vomitAoeArea, Epsilon epsilon){
        Aoe aoe = new Aoe(epsilon.getX() + new Random().nextInt(120), epsilon.getY() + + new Random().nextInt(120), 2000, 2, 40, 40, epsilon.getCurrentFrame());
        vomitAoeArea.add(aoe);
        aoe.getCurrentFrame().addToGamePanel(aoe);
        aoe.repaint();
    }
    protected void controlTheAreas(ArrayList<Aoe> vomitAoeArea, Epsilon epsilon){
        ArrayList<Aoe> expiredAoe = new ArrayList<>();
        for(Aoe aoe : vomitAoeArea){
            if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(aoe, epsilon) && System.currentTimeMillis() - aoe.getLastAttackFromMe() > 1000){
                epsilon.reduceHp(aoe.getPower(), AttackType.AOE, EntityType.ENEMY);
                aoe.setLastAttackFromMe(System.currentTimeMillis());
            }
            if(aoe.isExpired()){
                aoe.getCurrentFrame().removeFromGamePanel(aoe);
                expiredAoe.add(aoe);
            }
        }
        vomitAoeArea.removeAll(expiredAoe);
    }

}
