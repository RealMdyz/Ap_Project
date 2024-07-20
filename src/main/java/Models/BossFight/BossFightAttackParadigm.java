package Models.BossFight;

import Controller.BossFight.BossFightManger;
import Controller.Game.Intersection;
import Models.AttackType;
import Models.Enemy.Normal.Aoe;
import Models.EntityType;
import Models.Epsilon.Collectible;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import View.Game.GameFrame;

import java.security.BasicPermission;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BossFightAttackParadigm {

    private long lastSqueeze = 0;
    private long lastProjectile = 0;
    private long lastVomit = 0;
    private long lastPowerPunch = 0;
    private long lastQuake = 0;
    private long lastRapidFire = 0;
    private long lastSlap = 0;
    private static final long SQUEEZE_INTERVAL = 10000; // 10 seconds
    private static final long PROJECTILE_INTERVAL = 5000; // 5 seconds
    private static final long VOMIT_INTERVAL = 5000; // 5 seconds
    private static final int RADIUS_OF_QUAKE_IMPACT = 700;
    private static final long POWER_PUNCH_INTERVAL = 6000; // 5 seconds
    private static final long QUAKE_INTERVAL = 9000;
    private static final long RAPID_FIRE_INTERVAL = 30000;
    private static final long SLAP_INTERVAL = 2000;


    private static boolean onTheQuakeAttack = false;

    ArrayList<Aoe> vomitAoeArea = new ArrayList<>();

    ArrayList<Shot> shotsForProjectile = new ArrayList<>();

    public BossFightAttackParadigm(){

    }
    public void slapAttackManger(Epsilon epsilon, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if(smileyRightHand.getHp() > 0 && Math.random() < 0.0001 && lastSlap == 0){
            GameFrame gameFrame = smileyRightHand.getCurrentFrame();
            smileyRightHand.changeFrameAndPaint(epsilon.getCurrentFrame());
            smileyRightHand.setCurrentFrame(gameFrame);
            smileyRightHand.setX(epsilon.getX() + epsilon.getWidth() - Math.abs(new Random().nextInt(20)));
            smileyRightHand.setY(epsilon.getY() + epsilon.getHeight() - Math.abs(new Random().nextInt(20)));
            epsilon.doImpact(smileyRightHand.getX(), smileyRightHand.getY(), 150);
            epsilon.reduceHp(smileyRightHand.getPower(), AttackType.MELEE, EntityType.ENEMY);
            lastSlap = currentTime;
            System.out.println("Do Slap");
        }
        if(currentTime - lastSlap > SLAP_INTERVAL && lastSlap != 0){
            smileyRightHand.changeFrameAndPaint(smileyRightHand.getCurrentFrame());
            smileyRightHand.setRandomPosAfterGettingAttack();
            lastSlap = 0;
        }
    }
    public void rapidFireAttackManger(Epsilon epsilon, SmileyFace smileyFace){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastRapidFire < RAPID_FIRE_INTERVAL){
            if(Math.random() < 0.01){
                Shot shot = new Shot(smileyFace.getCenterX(), smileyFace.getCenterY(), smileyFace.getPower(), smileyFace.getCurrentFrame(), false);
                shot.getCurrentFrame().addToGamePanel(shot);
                shot.setRandomV();
                smileyFace.getRapidFireShots().add(shot);
                System.out.println("Rapid Is Fire !");
            }
        }
        else if(currentTime - lastRapidFire < 2 * RAPID_FIRE_INTERVAL){
            BossFightManger.setOpenAttackToSmileyFace(false);
        }
        else{
            lastRapidFire = currentTime;
            BossFightManger.setOpenAttackToSmileyFace(true);
        }
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
    public void quakeAttackManager(Epsilon epsilon, SmileyPunch smileyPunch){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastQuake < QUAKE_INTERVAL) {
            // Do Nothing

        }
        else if(currentTime - lastQuake < 3 * QUAKE_INTERVAL){
            onTheQuakeAttack = false;
        }
        else {
            // Reset the rigid state and vulnerability status after attack
            smileyPunch.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX() + epsilon.getCurrentFrame().getWidth() / 2, epsilon.getCurrentFrame().getY() + epsilon.getCurrentFrame().getHeight());
            epsilon.doImpact(epsilon.getCurrentFrame().getWidth() / 2, epsilon.getCurrentFrame().getHeight(), RADIUS_OF_QUAKE_IMPACT);
            lastQuake = currentTime;
            onTheQuakeAttack = true;
            System.out.println("Quake Executed!");
        }
    }

    public void powerPunchAttackManager(Epsilon epsilon, SmileyPunch smileyPunch, SmileyRightHand smileyRightHand, SmileyLeftHand smileyLeftHand){
        long currentTime = System.currentTimeMillis();

        // Determine if the attack can be performed based on timing or other conditions
        if(currentTime - lastPowerPunch < POWER_PUNCH_INTERVAL) {
            // Do Nothing
        }
        else if(currentTime - lastPowerPunch < 3 * POWER_PUNCH_INTERVAL){
            smileyLeftHand.getCurrentFrame().setSolb(false);
            smileyRightHand.getCurrentFrame().setSolb(false);
            BossFightManger.setOpenAttackToSmileyFace(false);
            BossFightManger.setOpenAttackToSmileyHands(false);
        }
        else {
            smileyLeftHand.getCurrentFrame().setSolb(true);
            smileyRightHand.getCurrentFrame().setSolb(true);

            // Determine which wall to strike
            int wallToHit = new Random().nextInt(4); // 0: top, 1: right, 2: bottom, 3: left
            switch (wallToHit) {
                case 0: // Top
                    smileyPunch.getCurrentFrame().setLocation(smileyPunch.getCurrentFrame().getX(), epsilon.getCurrentFrame().getY() - smileyPunch.getCurrentFrame().getHeight());
                    break;
                case 1: // Right
                    smileyPunch.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX() + epsilon.getCurrentFrame().getWidth(), smileyPunch.getCurrentFrame().getY());
                    break;
                case 2: // Bottom
                    smileyPunch.getCurrentFrame().setLocation(smileyPunch.getCurrentFrame().getX(), epsilon.getCurrentFrame().getY() + epsilon.getCurrentFrame().getHeight());
                    break;
                case 3: // Left
                    smileyPunch.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX() - smileyPunch.getCurrentFrame().getWidth(), smileyPunch.getCurrentFrame().getY());
                    break;
            }

            // Execute the punch
            epsilon.reduceFrameSize(wallToHit);

            // Make Smiley vulnerable during this attack
            BossFightManger.setOpenAttackToSmileyFace(true);
            BossFightManger.setOpenAttackToSmileyHands(true);
            // Update the last squeeze time
            lastPowerPunch = currentTime;
            System.out.println("Power Punch Executed!");
        }
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

    public static boolean isOnTheQuakeAttack() {
        return onTheQuakeAttack;
    }

    public static void setOnTheQuakeAttack(boolean onTheQuakeAttack) {
        BossFightAttackParadigm.onTheQuakeAttack = onTheQuakeAttack;
    }
}
