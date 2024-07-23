package Models.BossFight;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;

import java.util.ArrayList;

public class RapidFireLogic {
    protected void fire(SmileyFace smileyFace){
        Shot shot = new Shot(smileyFace.getCenterX(), smileyFace.getCenterY(), smileyFace.getPower(), smileyFace.getCurrentFrame(), false);
        shot.getCurrentFrame().addToGamePanel(shot);
        shot.setRandomV();
        smileyFace.getRapidFireShots().add(shot);
    }
    protected void manageTheShots(SmileyFace smileyFace, Epsilon epsilon){
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        for(Shot shot : smileyFace.getRapidFireShots()){
            shot.move();
            if(Intersection.isInThisFrame(shot, epsilon.getCurrentFrame()) && !shot.getCurrentFrame().equals(epsilon.getCurrentFrame())){
                shot.changeFrameAndPaint(epsilon.getCurrentFrame());

            }
            else if (shot.getXRelativeToTheScreen() < -200 || shot.getXRelativeToTheScreen() > 2500 || shot.getYRelativeToTheScreen() < -200 || shot.getYRelativeToTheScreen()> 2500){
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
            else if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(shot, epsilon)){
                epsilon.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.ENEMY);
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
        smileyFace.getRapidFireShots().removeAll(shotArrayList);
    }
}
