package Controller.Game;

import Models.Enemy.Enemy;
import Models.Enemy.Necropick;
import Models.Epsilon.Shot;
import Models.Game;
import java.util.Iterator;

public class ShotController {
    private Game game;
    public ShotController(Game game){
        this.game = game;
    }
    public void checkShot() {
        if (game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1) {
            // Epsilon Shot Happen!
        }
    }
    public void shotMove(){
        epsilonShotMove();
        necropickShotMove();
    }

    public void epsilonShotMove(){

    }

    public void necropickShotMove(){

    }


}
