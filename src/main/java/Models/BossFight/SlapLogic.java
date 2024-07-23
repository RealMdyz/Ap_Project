package Models.BossFight;

import Models.AttackType;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import View.Game.GameFrame;

import java.util.Random;

public class SlapLogic {

    protected void startTheSlap(SmileyRightHand smileyRightHand, Epsilon epsilon) {
        GameFrame gameFrame = smileyRightHand.getCurrentFrame();
        smileyRightHand.changeFrameAndPaint(epsilon.getCurrentFrame());
        smileyRightHand.setCurrentFrame(gameFrame);
        smileyRightHand.setX(epsilon.getX() + epsilon.getWidth() - Math.abs(new Random().nextInt(20)));
        smileyRightHand.setY(epsilon.getY() + epsilon.getHeight() - Math.abs(new Random().nextInt(20)));
        epsilon.doImpact(smileyRightHand.getX(), smileyRightHand.getY(), 150);
        epsilon.reduceHp(smileyRightHand.getPower(), AttackType.MELEE, EntityType.ENEMY);
    }

    protected void endTheSlap(SmileyRightHand smileyRightHand) {
        smileyRightHand.changeFrameAndPaint(smileyRightHand.getCurrentFrame());
        smileyRightHand.setRandomPosAfterGettingAttack();
    }
}