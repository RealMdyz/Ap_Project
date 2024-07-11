package Controller.Menu;

import Models.Constant;
import Models.Game;

import javax.swing.*;

public class UpdateToPPanel {

    private final Game game;
    private final Constant constant;
    public UpdateToPPanel(Game game, Constant constant){
        this.game = game;
        this.constant = constant;
    }

    public void updateTopPanel(long startOfGame, int currentWaveIndex, int endWaveIndex, int xp, int hp){
        if(currentWaveIndex >= 5)
            currentWaveIndex = 4;
        game.getTopPanel().updateXPLabel(Constant.getPlayerXP());
        game.getTopPanel().updateTimeLabel((System.currentTimeMillis() - startOfGame));
        game.getTopPanel().updateWaveLabel(currentWaveIndex, endWaveIndex);
        game.getTopPanel().updateXPLabel(Constant.getPlayerXP());
        game.getTopPanel().updateHPLabel(hp);
        ImageIcon imageIcon = new ImageIcon("Game/Writ of Astrape.png");
        game.getTopPanel().updateAbilityAttackIcon(imageIcon);
    }
}
