package Controller.BossFight;

import Controller.Game.FrameIntersection;
import Models.BossFight.*;
import Models.Constant;
import Models.Epsilon.Collectible;
import Models.Epsilon.Epsilon;
import Models.Game;
import View.Game.GameFrame;

import java.awt.*;
import java.io.Console;

public class BossFightManger {
    SmileyFace smileyFace;
    SmileyRightHand smileyRightHand;
    SmileyLeftHand smileyLeftHand;
    SmileyPunch smileyPunch;
    public static boolean openAttackToSmileyFace = false;
    public static boolean openAttackToSmileyHands = false;
    BossFightAttackParadigm bossFightAttackParadigm;
    FirstSpawnBossFight firstSpawnBossFight;
    public BossFightManger(BossFightAttackParadigm bossFightAttackParadigm){
        this.bossFightAttackParadigm = bossFightAttackParadigm;
        firstSpawnBossFight = new FirstSpawnBossFight();
    }
    public void control(Game game){
        if(smileyFace.getHp() <= 0)
            return;
        if(!firstSpawnBossFight.isFirstSpawnOfBossFight()){
            firstSpawnBossFight.firstSpawn(smileyFace, smileyRightHand, smileyLeftHand,game);
        }
        else{
            if(smileyRightHand.getHp() > 0 && smileyLeftHand.getHp() > 0){
                bossFightAttackParadigm.squeezeAttackManager(game.getEpsilon(), smileyFace, smileyLeftHand, smileyRightHand);
                bossFightAttackParadigm.projectileAttackManger(game.getEpsilon(), smileyFace, smileyLeftHand, smileyRightHand);
            }
            if(smileyFace.getHp() < Constant.SPAWN_SMILEY_PUNCH_HP && smileyPunch != null){
                bossFightAttackParadigm.vomitAttackManger(game.getEpsilon(), smileyFace, smileyLeftHand, smileyRightHand);
                bossFightAttackParadigm.powerPunchAttackManager(game.getEpsilon(), smileyPunch, smileyRightHand, smileyLeftHand);
                bossFightAttackParadigm.quakeAttackManager(game.getEpsilon(), smileyPunch);
                bossFightAttackParadigm.slapAttackManger(game.getEpsilon(), smileyRightHand);
                bossFightAttackParadigm.rapidFireAttackManger(game.getEpsilon(), smileyFace);
                bossFightAttackParadigm.slapAttackManger(game.getEpsilon(), smileyRightHand);
                bossFightAttackParadigm.rapidFireAttackManger(game.getEpsilon(), smileyFace);
            }


        }

        checkTheBossFightState();
        checkTheStateOfTheSmileyChuck(game);
    }
    private void checkTheBossFightState(){
        if(smileyRightHand.getHp() < 0 && smileyRightHand != null){
            smileyRightHand.getCurrentFrame().setVisible(false);
            smileyFace.changeFrameAndPaint(smileyFace.getCurrentFrame());
            smileyRightHand = null;
        }
        if(smileyLeftHand.getHp() < 0 && smileyLeftHand != null){
            smileyRightHand.getCurrentFrame().setVisible(false);
            smileyFace.changeFrameAndPaint(smileyFace.getCurrentFrame());
            smileyLeftHand = null;
        }
    }
    public void trigger(Game game){
        GameFrame smileyFaceFrame = new GameFrame(200, 200, false, false);
        smileyFaceFrame.setLocation(600, 0);
        GameFrame smileyRightHandFrame = new GameFrame(200, 200, false, false);
        smileyRightHandFrame.setLocation(game.getEpsilon().getCurrentFrame().getX() +  game.getEpsilon().getCurrentFrame().getWidth(), 400);
        GameFrame smileyLeftHandFrame = new GameFrame(200, 200, false, false);
        smileyLeftHandFrame.setLocation(game.getEpsilon().getCurrentFrame().getX() - 200, 400);
        smileyFace = new SmileyFace(60, 60, smileyFaceFrame, game.getEpsilon());
        smileyLeftHand = new SmileyLeftHand(60, 60, smileyLeftHandFrame, game.getEpsilon());
        smileyRightHand = new SmileyRightHand(60, 60, smileyRightHandFrame, game.getEpsilon());
        smileyFaceFrame.addToGamePanel(smileyFace);
        smileyRightHandFrame.addToGamePanel(smileyRightHand);
        smileyLeftHandFrame.addToGamePanel(smileyLeftHand);
        smileyFaceFrame.setVisible(true);
        smileyRightHandFrame.setVisible(false);
        smileyLeftHandFrame.setVisible(false);
    }
    public void triggerPunch(Game game){
        GameFrame smileyPunchFrame = new GameFrame(200, 200, false, false);
        smileyPunchFrame.setLocation(1250, 600);
        smileyPunch = new SmileyPunch(60, 60, smileyPunchFrame, game.getEpsilon());
        smileyPunchFrame.addToGamePanel(smileyPunch);
        smileyPunchFrame.setVisible(true);
        game.getEpsilon().getCurrentFrame().requestFocus();
        game.getGameFrames().add(smileyPunchFrame);

    }
    public void checkTheStateOfTheSmileyChuck(Game game){
        if(smileyFace.getHp() <  Constant.SPAWN_SMILEY_PUNCH_HP && smileyPunch == null){
            triggerPunch(game);
        }
    }
    public SmileyFace getSmileyFace() {
        return smileyFace;
    }

    public void setSmileyFace(SmileyFace smileyFace) {
        this.smileyFace = smileyFace;
    }

    public SmileyRightHand getSmileyRightHand() {
        return smileyRightHand;
    }

    public void setSmileyRightHand(SmileyRightHand smileyRightHand) {
        this.smileyRightHand = smileyRightHand;
    }

    public SmileyLeftHand getSmileyLeftHand() {
        return smileyLeftHand;
    }

    public void setSmileyLeftHand(SmileyLeftHand smileyLeftHand) {
        this.smileyLeftHand = smileyLeftHand;
    }


    public static boolean isOpenAttackToSmileyFace() {
        return openAttackToSmileyFace;
    }

    public static void setOpenAttackToSmileyFace(boolean openAttackToSmileyFace) {
        BossFightManger.openAttackToSmileyFace = openAttackToSmileyFace;
    }

    public static boolean isOpenAttackToSmileyHands() {
        return openAttackToSmileyHands;
    }

    public static void setOpenAttackToSmileyHands(boolean openAttackToSmileyHands) {
        BossFightManger.openAttackToSmileyHands = openAttackToSmileyHands;
    }

    public FirstSpawnBossFight getFirstSpawnBossFight() {
        return firstSpawnBossFight;
    }

    public void setFirstSpawnBossFight(FirstSpawnBossFight firstSpawnBossFight) {
        this.firstSpawnBossFight = firstSpawnBossFight;
    }
}
