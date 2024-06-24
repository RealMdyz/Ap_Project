package Controller.Menu;

import Models.Constant;
import Models.Game;

public class UpdateToPPanel {

    private final Game game;
    private final Constant constant;
    public UpdateToPPanel(Game game, Constant constant){
        this.game = game;
        this.constant = constant;
    }

    public void updateTopPanel(long startOfGame, int currentWaveIndex, int endWaveIndex){
        game.getTopPanel().updateXPLabel(Constant.getPlayerXP());
        game.getTopPanel().updateTimeLabel((System.currentTimeMillis() - startOfGame));
        game.getTopPanel().updateWaveLabel(currentWaveIndex, endWaveIndex);
    }
}
