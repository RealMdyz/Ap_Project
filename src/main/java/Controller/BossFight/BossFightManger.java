package Controller.BossFight;

import Controller.Game.FrameIntersection;
import Models.BossFight.SmileyFace;
import Models.BossFight.SmileyLeftHand;
import Models.BossFight.SmileyRightHand;
import Models.Constant;
import Models.Epsilon.Collectible;
import Models.Game;
import View.Game.GameFrame;

import java.io.Console;

public class BossFightManger {
    SmileyFace smileyFace;
    SmileyRightHand smileyRightHand;
    SmileyLeftHand smileyLeftHand;
    private  boolean firstSpawnOfBossFight = false;
    private boolean intersectWithEpsilonFrameAtTheStart = false;
    public BossFightManger(){

    }
    public void control(Game game){
        if(!firstSpawnOfBossFight){
            if(!intersectWithEpsilonFrameAtTheStart)
                 smileyFace.getCurrentFrame().setLocation(smileyFace.getCurrentFrame().getX() + 2, smileyFace.getCurrentFrame().getY() + 2);
            else{
                smileyRightHand.getCurrentFrame().setVisible(true);
                smileyLeftHand.getCurrentFrame().setVisible(true);
                firstSpawnOfBossFight = true;
            }
            if(FrameIntersection.twoFrameIntersection(smileyFace.getCurrentFrame(), game.getEpsilon().getCurrentFrame())){
                intersectWithEpsilonFrameAtTheStart = true;
                smileyFace.getCurrentFrame().setLocation(smileyFace.getCurrentFrame().getX() - 2, smileyFace.getCurrentFrame().getY() - 2);
            }
        }
    }
    public void trigger(Game game){
        GameFrame smileyFaceFrame = new GameFrame(200, 200, false, false);
        smileyFaceFrame.setLocation(600, 0);
        GameFrame smileyRightHandFrame = new GameFrame(200, 200, false, false);
        smileyRightHandFrame.setLocation(game.getEpsilon().getCurrentFrame().getX() +  game.getEpsilon().getCurrentFrame().getWidth(), 400);
        GameFrame smileyLeftHandFrame = new GameFrame(200, 200, false, false);
        smileyLeftHandFrame.setLocation(game.getEpsilon().getCurrentFrame().getX() - 200, 400);
        smileyFace = new SmileyFace(60, 60, smileyFaceFrame);
        smileyLeftHand = new SmileyLeftHand(60, 60, smileyLeftHandFrame);
        smileyRightHand = new SmileyRightHand(60, 60, smileyRightHandFrame);
        smileyFaceFrame.addToGamePanel(smileyFace);
        smileyRightHandFrame.addToGamePanel(smileyRightHand);
        smileyLeftHandFrame.addToGamePanel(smileyLeftHand);
        smileyFaceFrame.setVisible(true);
        smileyRightHandFrame.setVisible(false);
        smileyLeftHandFrame.setVisible(false);
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

    public boolean isFirstSpawnOfBossFight() {
        return firstSpawnOfBossFight;
    }

    public void setFirstSpawnOfBossFight(boolean firstSpawnOfBossFight) {
        this.firstSpawnOfBossFight = firstSpawnOfBossFight;
    }
}
