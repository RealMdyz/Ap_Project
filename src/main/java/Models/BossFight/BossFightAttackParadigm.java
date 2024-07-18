package Models.BossFight;

import Controller.BossFight.BossFightManger;
import Controller.Game.Intersection;
import Models.AttackType;
import Models.Enemy.Normal.Aoe;
import Models.EntityType;
import Models.Epsilon.Collectible;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;

import java.security.BasicPermission;
import java.util.ArrayList;
import java.util.Random;

public class BossFightAttackParadigm {

    private long lastSqueeze = 0;
    private long lastProjectile = 0;
    private long lastVomit = 0;
    private static final long SQUEEZE_INTERVAL = 10000; // 10 seconds
    private static final long PROJECTILE_INTERVAL = 5000; // 5 seconds
    private static final long VOMIT_INTERVAL = 5000; // 5 seconds

    ArrayList<Aoe> vomitAoeArea = new ArrayList<>();


    ArrayList<Shot> shotsForProjectile = new ArrayList<>();

    public BossFightAttackParadigm(){

    }
    public void powerPunchAttackManager(Epsilon epsilon, SmileyPunch smileyPunch){

    }
    public void quakeAttackManager(Epsilon epsilon, SmileyPunch smileyPunch){

    }
    public void vomitAttackManger(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastVomit < VOMIT_INTERVAL && !smileyFace.isInEpsilonFrameForProjectile){
            // Do Vomit Attack
            if(Math.random() < 0.01){
                Aoe aoe = new Aoe(epsilon.getX() + new Random().nextInt(120), epsilon.getY() + + new Random().nextInt(120), 2000, 2, 40, 40, epsilon.getCurrentFrame());
                vomitAoeArea.add(aoe);
                aoe.getCurrentFrame().addToGamePanel(aoe);
                aoe.repaint();
                System.out.println("DoVomitAttack");
            }
        }
        else if(currentTime - lastVomit < 2 * VOMIT_INTERVAL){
            // Do Nothing
            BossFightManger.setOpenAttackToSmileyHands(false);
        }
        else{
            BossFightManger.setOpenAttackToSmileyHands(true);
            lastVomit = currentTime;
        }
        ArrayList<Aoe> expiredAoe = new ArrayList<>();
        for(Aoe aoe : vomitAoeArea){
            if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(aoe, epsilon) && currentTime - aoe.getLastAttackFromMe() > 1000){
                epsilon.reduceHp(aoe.getPower(), AttackType.AOE, EntityType.ENEMY);
                aoe.setLastAttackFromMe(currentTime);
            }
            if(aoe.isExpired()){
                aoe.getCurrentFrame().removeFromGamePanel(aoe);
                expiredAoe.add(aoe);
            }
        }
        vomitAoeArea.removeAll(expiredAoe);
    }
    public void squeezeAttackManager(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSqueeze > SQUEEZE_INTERVAL && currentTime - lastSqueeze < 3 * SQUEEZE_INTERVAL) {
            smileyLeftHand.getCurrentFrame().setSolb(false);
            smileyRightHand.getCurrentFrame().setSolb(false);
            BossFightManger.setOpenAttackToSmileyFace(false);
            return; // Not enough time has passed since the last squeeze attack
        }
        else if(currentTime - lastSqueeze < SQUEEZE_INTERVAL){
            // attack in process!
        }
        else if (epsilon.getXRelativeToTheScreen() + 30 > smileyFace.getXRelativeToTheScreen() && epsilon.getXRelativeToTheScreen() - 30 < smileyFace.getXRelativeToTheScreen()) {
            // Check if Epsilon is between Smiley's hands
            if (smileyFace.getXRelativeToTheScreen() > smileyLeftHand.getXRelativeToTheScreen() && smileyFace.getXRelativeToTheScreen() < smileyRightHand.getXRelativeToTheScreen()) {
                // Execute squeeze attack
                lastSqueeze = currentTime;
                // Add logic to handle squeeze attack (e.g., inflict damage to Epsilon)
                smileyLeftHand.getCurrentFrame().setSolb(true);
                smileyRightHand.getCurrentFrame().setSolb(true);
                smileyRightHand.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX()+ epsilon.getCurrentFrame().getWidth(),smileyRightHand.getCurrentFrame().getY());
                smileyLeftHand.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX() - smileyLeftHand.getCurrentFrame().getWidth(), smileyLeftHand.getCurrentFrame().getY());
                BossFightManger.setOpenAttackToSmileyFace(true);
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
                Shot shot2 = new Shot(1500, smileyRightHand.getYRelativeToTheScreen() - epsilon.getCurrentFrame().getY(), 5, epsilon.getCurrentFrame(), false);
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
            BossFightManger.setOpenAttackToSmileyHands(false);

        }
        else{
            lastProjectile = currentTime;
            smileyFace.getCurrentFrame().removeFromGamePanel(smileyFace);
            epsilon.getCurrentFrame().addToGamePanel(smileyFace);
            smileyFace.isInEpsilonFrameForProjectile = true;
            BossFightManger.setOpenAttackToSmileyHands(true);
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
