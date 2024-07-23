package Models.BossFight;

import Models.Epsilon.Epsilon;

import java.util.Random;

public class PowerPunchLogic {
    protected void doAPunch(SmileyPunch smileyPunch, Epsilon epsilon){
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
    }
}
