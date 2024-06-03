package Controller.Game;

import Models.Constant;
import Models.Game;

public class StoreController {
    private Game game;
    public StoreController(Game game){
        this.game = game;
    }
    public void openStore(){
        game.getStorePanel().setVisible(Constant.isOpenStore());
    }
}
