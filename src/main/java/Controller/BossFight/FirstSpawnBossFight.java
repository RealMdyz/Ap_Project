package Controller.BossFight;

import Controller.Game.FrameIntersection;
import Controller.Game.GameLoop;
import Models.BossFight.SmileyFace;
import Models.BossFight.SmileyLeftHand;
import Models.BossFight.SmileyRightHand;
import Models.Game;
import View.Game.GameFrame;

public class FirstSpawnBossFight {

    private  boolean firstSpawnOfBossFight = false;
    private boolean intersectWithEpsilonFrameAtTheStart = false;
    public FirstSpawnBossFight() {
    }

    public void firstSpawn(SmileyFace smileyFace, SmileyRightHand smileyRightHand, SmileyLeftHand smileyLeftHand, Game game){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(!intersectWithEpsilonFrameAtTheStart)
            smileyFace.getCurrentFrame().setLocation(smileyFace.getCurrentFrame().getX() + 1, smileyFace.getCurrentFrame().getY() + 1);
        else{
            smileyRightHand.getCurrentFrame().setVisible(true);
            smileyLeftHand.getCurrentFrame().setVisible(true);
            firstSpawnOfBossFight = true;
            game.getGameFrames().add(smileyFace.getCurrentFrame());
            game.getGameFrames().add(smileyLeftHand.getCurrentFrame());
            game.getGameFrames().add(smileyRightHand.getCurrentFrame());
            game.getEpsilon().getCurrentFrame().requestFocus();
        }
        if(FrameIntersection.twoFrameIntersection(smileyFace.getCurrentFrame(), game.getEpsilon().getCurrentFrame())){
            intersectWithEpsilonFrameAtTheStart = true;
            smileyFace.getCurrentFrame().setLocation(smileyFace.getCurrentFrame().getX() - 1, smileyFace.getCurrentFrame().getY() - 1);
        }
    }

    public boolean isFirstSpawnOfBossFight() {
        return firstSpawnOfBossFight;
    }

    public void setFirstSpawnOfBossFight(boolean firstSpawnOfBossFight) {
        this.firstSpawnOfBossFight = firstSpawnOfBossFight;
    }

    public boolean isIntersectWithEpsilonFrameAtTheStart() {
        return intersectWithEpsilonFrameAtTheStart;
    }

    public void setIntersectWithEpsilonFrameAtTheStart(boolean intersectWithEpsilonFrameAtTheStart) {
        this.intersectWithEpsilonFrameAtTheStart = intersectWithEpsilonFrameAtTheStart;
    }
}
