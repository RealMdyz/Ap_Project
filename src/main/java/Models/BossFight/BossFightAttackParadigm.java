package Models.BossFight;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;

import java.util.ArrayList;

public class BossFightAttackParadigm {

    private long lastSqueeze = 0;
    private long lastProjectile = 0;
    private static final long SQUEEZE_INTERVAL = 10000; // 10 seconds
    private static final long PROJECTILE_INTERVAL = 5000; // 30 seconds


    ArrayList<Shot> shotsForProjectile = new ArrayList<>();

    public BossFightAttackParadigm(){

    }
    public void squeezeAttackManager(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSqueeze < SQUEEZE_INTERVAL) {
            smileyLeftHand.getCurrentFrame().setSolb(false);
            smileyRightHand.getCurrentFrame().setSolb(false);
            return; // Not enough time has passed since the last squeeze attack
        }
        if (epsilon.getXRelativeToTheScreen() + 30 > smileyFace.getXRelativeToTheScreen() && epsilon.getXRelativeToTheScreen() - 30 < smileyFace.getXRelativeToTheScreen()) {
            // Check if Epsilon is between Smiley's hands
            if (smileyFace.getXRelativeToTheScreen() > smileyLeftHand.getXRelativeToTheScreen() && smileyFace.getXRelativeToTheScreen() < smileyRightHand.getXRelativeToTheScreen()) {
                // Execute squeeze attack
                lastSqueeze = currentTime;
                // Add logic to handle squeeze attack (e.g., inflict damage to Epsilon)
                smileyLeftHand.getCurrentFrame().setSolb(true);
                smileyRightHand.getCurrentFrame().setSolb(true);
                smileyRightHand.setY(epsilon.getCurrentFrame().getY());
                smileyLeftHand.setY(epsilon.getCurrentFrame().getY() - smileyLeftHand.getWidth());
                System.out.println("Squeeze Attack Executed!");
            }
        }
    }
    public void projectileAttackManger(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastProjectile < PROJECTILE_INTERVAL) {
            smileyFace.move(epsilon);
            // Do the projectileAttack
            if(Math.random() < 0.005){
                Shot shot1 = new Shot(-100, smileyLeftHand.getYRelativeToTheScreen() - epsilon.getCurrentFrame().getY(), 5, epsilon.getCurrentFrame(), false);
                shot1.setxVelocity(+5);
                shot1.setyVelocity(0);
                shot1.getCurrentFrame().addToGamePanel(shot1);
                Shot shot2 = new Shot(1500, smileyLeftHand.getYRelativeToTheScreen() - epsilon.getCurrentFrame().getY(), 5, epsilon.getCurrentFrame(), false);
                shot2.setxVelocity(-5);
                shot2.setyVelocity(0);
                shot2.getCurrentFrame().addToGamePanel(shot2);
                shotsForProjectile.add(shot2);
                shotsForProjectile.add(shot1);
                System.out.println("ShotFireProjectile!");
            }
        }
        else if(currentTime - lastProjectile < 10 * PROJECTILE_INTERVAL){
            // Do Nothing
            if(smileyFace.isInEpsilonFrameForProjectile){
                smileyFace.setX(80);
                smileyFace.setY(80);
                smileyFace.isInEpsilonFrameForProjectile = false;
                smileyFace.getCurrentFrame().addToGamePanel(smileyFace);
                epsilon.getCurrentFrame().removeFromGamePanel(smileyFace);
            }

        }
        else{
            lastProjectile = currentTime;
            smileyFace.getCurrentFrame().removeFromGamePanel(smileyFace);
            epsilon.getCurrentFrame().addToGamePanel(smileyFace);
            smileyFace.isInEpsilonFrameForProjectile = true;
        }

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

}
