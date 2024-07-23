package Models.BossFight;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;

import java.util.ArrayList;

public class ProjectileLogic {
    protected void fire(SmileyRightHand smileyRightHand, SmileyLeftHand smileyLeftHand, ArrayList<Shot> shotsForProjectile, Epsilon epsilon){
        if(smileyRightHand != null){
            Shot shot1 = new Shot(-100, smileyLeftHand.getYRelativeToTheScreen() - epsilon.getCurrentFrame().getY(), 5, epsilon.getCurrentFrame(), false);
            shot1.setxVelocity(+5);
            shot1.setyVelocity(0);
            shot1.getCurrentFrame().addToGamePanel(shot1);
            shotsForProjectile.add(shot1);
        }
        if(smileyLeftHand != null){
            Shot shot2 = new Shot(1500, smileyRightHand.getYRelativeToTheScreen() - epsilon.getCurrentFrame().getY(), 5, epsilon.getCurrentFrame(), false);
            shot2.setxVelocity(-5);
            shot2.setyVelocity(0);
            shot2.getCurrentFrame().addToGamePanel(shot2);
            shotsForProjectile.add(shot2);
        }
    }
    protected void setSmileyFace(SmileyFace smileyFace, Epsilon epsilon){
        smileyFace.setX(80);
        smileyFace.setY(80);
        smileyFace.isInEpsilonFrameForProjectile = false;
        smileyFace.getCurrentFrame().addToGamePanel(smileyFace);
        epsilon.getCurrentFrame().removeFromGamePanel(smileyFace);
    }
    protected void controlTheShots(Epsilon epsilon,  ArrayList<Shot> shotsForProjectile){
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        for(Shot shot : shotsForProjectile){
            shot.move();
            shot.repaint();
            if(shot.getX() > 1800 || shot.getX() < -200){
                shot.getCurrentFrame().removeFromGamePanel(shot);
                shotArrayList.add(shot);
            }
            else if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(shot, epsilon)){
                epsilon.reduceHp(5, AttackType.RANGED, EntityType.ENEMY);
                shot.getCurrentFrame().removeFromGamePanel(shot);
                shotArrayList.add(shot);
            }
        }
        shotsForProjectile.removeAll(shotArrayList);
    }
    protected void setTheStartStateOfProjectile(SmileyFace smileyFace, Epsilon epsilon){
        smileyFace.getCurrentFrame().removeFromGamePanel(smileyFace);
        epsilon.getCurrentFrame().addToGamePanel(smileyFace);
        smileyFace.isInEpsilonFrameForProjectile = true;
    }
}
