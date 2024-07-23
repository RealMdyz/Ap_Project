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
    private boolean faceInUseForRapidFire = false;
    private boolean faceInUseForProjectile = false;
    private static final long SQUEEZE_INTERVAL = 10000; // 10 seconds
    private static final long PROJECTILE_INTERVAL = 5000; // 5 seconds
    private static final long VOMIT_INTERVAL = 5000; // 5 seconds
    private static final int RADIUS_OF_QUAKE_IMPACT = 500;
    private static final long POWER_PUNCH_INTERVAL = 6000; // 5 seconds
    private static final long QUAKE_INTERVAL = 9000;
    private static final long RAPID_FIRE_INTERVAL = 30000;
    private static final long SLAP_INTERVAL = 2000;
    private static boolean onTheQuakeAttack = false;

    ArrayList<Aoe> vomitAoeArea = new ArrayList<>();

    ArrayList<Shot> shotsForProjectile = new ArrayList<>();

    SlapLogic slapLogic;
    RapidFireLogic rapidFireLogic;
    PowerPunchLogic powerPunchLogic;
    VomitLogic vomitLogic;
    ProjectileLogic projectileLogic;
    public BossFightAttackParadigm(){
        slapLogic = new SlapLogic();
        rapidFireLogic = new RapidFireLogic();
        powerPunchLogic = new PowerPunchLogic();
        vomitLogic = new VomitLogic();
        projectileLogic = new ProjectileLogic();
    }
    public void slapAttackManger(Epsilon epsilon, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if(smileyRightHand.getHp() > 0 && Math.random() < 0.00005 && lastSlap == 0){
            slapLogic.startTheSlap(smileyRightHand, epsilon);
            lastSlap = currentTime;
            BossFightManger.setOpenAttackToSmileyFace(true);

        }
        if(currentTime - lastSlap > SLAP_INTERVAL && lastSlap != 0){
            slapLogic.endTheSlap(smileyRightHand);
            BossFightManger.setOpenAttackToSmileyFace(false);
            lastSlap = 0;
        }
    }
    public void rapidFireAttackManger(Epsilon epsilon, SmileyFace smileyFace){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastRapidFire < RAPID_FIRE_INTERVAL && !faceInUseForProjectile){
            BossFightManger.setOpenAttackToSmileyFace(true);
            if(Math.random() < 0.005){
                rapidFireLogic.fire(smileyFace);
            }
        }
        else if(currentTime - lastRapidFire < 2 * RAPID_FIRE_INTERVAL){
            faceInUseForRapidFire = false;
            BossFightManger.setOpenAttackToSmileyFace(false);
        }
        else{
            faceInUseForRapidFire = true;
            lastRapidFire = currentTime;
            BossFightManger.setOpenAttackToSmileyFace(true);
        }
        rapidFireLogic.manageTheShots(smileyFace, epsilon);
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
        }
    }

    public void powerPunchAttackManager(Epsilon epsilon, SmileyPunch smileyPunch, SmileyRightHand smileyRightHand, SmileyLeftHand smileyLeftHand){
        long currentTime = System.currentTimeMillis();

        // Determine if the attack can be performed based on timing or other conditions
        if(currentTime - lastPowerPunch < POWER_PUNCH_INTERVAL) {
            // Do Nothing
            BossFightManger.setOpenAttackToSmileyFace(true);
            BossFightManger.setOpenAttackToSmileyHands(true);
        }
        else if(currentTime - lastPowerPunch < 3 * POWER_PUNCH_INTERVAL){
            if(smileyLeftHand != null)
                smileyLeftHand.getCurrentFrame().setSolb(false);
            if(smileyRightHand != null)
                smileyRightHand.getCurrentFrame().setSolb(false);
            BossFightManger.setOpenAttackToSmileyFace(false);
            BossFightManger.setOpenAttackToSmileyHands(false);
        }
        else {
            if(smileyLeftHand != null)
                smileyLeftHand.getCurrentFrame().setSolb(true);
            if(smileyRightHand != null)
                smileyRightHand.getCurrentFrame().setSolb(true);
            powerPunchLogic.doAPunch(smileyPunch, epsilon);

            // Make Smiley vulnerable during this attack
            BossFightManger.setOpenAttackToSmileyFace(true);
            BossFightManger.setOpenAttackToSmileyHands(true);
            // Update the last squeeze time
            lastPowerPunch = currentTime;
        }
    }

    public void vomitAttackManger(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastVomit < VOMIT_INTERVAL && !smileyFace.isInEpsilonFrameForProjectile){
            BossFightManger.setOpenAttackToSmileyHands(true);
            // Do Vomit Attack
            if(Math.random() < 0.05){
                vomitLogic.doAVomit(vomitAoeArea, epsilon);
            }
        }
        else if(currentTime - lastVomit < 3 * VOMIT_INTERVAL){
            // Do Nothing
            BossFightManger.setOpenAttackToSmileyHands(false);
        }
        else{
            BossFightManger.setOpenAttackToSmileyHands(true);
            lastVomit = currentTime;
        }
        vomitLogic.controlTheAreas(vomitAoeArea, epsilon);
    }
    public void squeezeAttackManager(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSqueeze > SQUEEZE_INTERVAL && currentTime - lastSqueeze < 3 * SQUEEZE_INTERVAL) {
            if(smileyLeftHand != null)
                smileyLeftHand.getCurrentFrame().setSolb(false);
            if(smileyRightHand != null)
                smileyRightHand.getCurrentFrame().setSolb(false);
            BossFightManger.setOpenAttackToSmileyFace(false);
            return; // Not enough time has passed since the last squeeze attack
        }
        else if(currentTime - lastSqueeze < SQUEEZE_INTERVAL){
            // attack in process!
            BossFightManger.setOpenAttackToSmileyFace(true);
        }
        else if (epsilon.getXRelativeToTheScreen() + 30 > smileyFace.getXRelativeToTheScreen() && epsilon.getXRelativeToTheScreen() - 30 < smileyFace.getXRelativeToTheScreen()) {
            // Check if Epsilon is between Smiley's hands
            if (smileyFace.getXRelativeToTheScreen() > smileyLeftHand.getXRelativeToTheScreen() && smileyFace.getXRelativeToTheScreen() < smileyRightHand.getXRelativeToTheScreen()) {
                // Execute squeeze attack
                lastSqueeze = currentTime;
                // Add logic to handle squeeze attack (e.g., inflict damage to Epsilon)
                if(smileyLeftHand != null)
                    smileyLeftHand.getCurrentFrame().setSolb(true);
                if(smileyRightHand != null)
                    smileyRightHand.getCurrentFrame().setSolb(true);
                smileyRightHand.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX()+ epsilon.getCurrentFrame().getWidth(),smileyRightHand.getCurrentFrame().getY());
                smileyLeftHand.getCurrentFrame().setLocation(epsilon.getCurrentFrame().getX() - smileyLeftHand.getCurrentFrame().getWidth(), smileyLeftHand.getCurrentFrame().getY());
                BossFightManger.setOpenAttackToSmileyFace(true);
            }
        }
    }
    public void projectileAttackManger(Epsilon epsilon, SmileyFace smileyFace, SmileyLeftHand smileyLeftHand, SmileyRightHand smileyRightHand){
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastProjectile < PROJECTILE_INTERVAL && !faceInUseForRapidFire) {
            smileyFace.move(epsilon);
            BossFightManger.setOpenAttackToSmileyHands(true);
            // Do the projectileAttack
            if(Math.random() < 0.01){
                projectileLogic.fire(smileyRightHand, smileyLeftHand, shotsForProjectile, epsilon);
            }
        }
        else if(currentTime - lastProjectile < 3 * PROJECTILE_INTERVAL){
            // Do Nothing
            if(smileyFace.isInEpsilonFrameForProjectile){
                faceInUseForProjectile = false;
                projectileLogic.setSmileyFace(smileyFace, epsilon);
            }
            BossFightManger.setOpenAttackToSmileyHands(false);

        }
        else{
            faceInUseForProjectile = true;
            lastProjectile = currentTime;
            projectileLogic.setTheStartStateOfProjectile(smileyFace, epsilon);
            BossFightManger.setOpenAttackToSmileyHands(true);
        }
        projectileLogic.controlTheShots(epsilon, shotsForProjectile);

    }

    public static boolean isOnTheQuakeAttack() {
        return onTheQuakeAttack;
    }

    public static void setOnTheQuakeAttack(boolean onTheQuakeAttack) {
        BossFightAttackParadigm.onTheQuakeAttack = onTheQuakeAttack;
    }
}
