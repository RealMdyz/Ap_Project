package Models.Games;

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
    ImageIcon imageIconWritOfAres = new ImageIcon("Game/Writ of Ares.png");
    ImageIcon imageIconWritOfAstrape = new ImageIcon("Game/Writ of Astrape.png");
    ImageIcon imageIconWritOfCerberus = new ImageIcon("Game/Writ of Cerberus.png");
    ImageIcon imageIconProteus = new ImageIcon("Game/Writ of Proteus.png");

    ImageIcon imageIconEmpusa = new ImageIcon("Game/Writ of Empusa.png");

    ImageIcon imageIconDolus = new ImageIcon("Game/Writ of Dolus.png");
    ImageIcon imageIconAceso = new ImageIcon("Game/Writ of Aceso.png");
    ImageIcon imageIconMelampus = new ImageIcon("Game/Writ of Melampus.png");

    ImageIcon imageIconChiron = new ImageIcon("Game/Writ of Chiron.png");



    public void updateTopPanel(long startOfGame, int currentWaveIndex, int endWaveIndex, int hp){
        if(currentWaveIndex >= 5)
            currentWaveIndex = 4;
        game.getTopPanel().updateXPLabel(Constant.getPlayerXP());
        game.getTopPanel().updateTimeLabel((System.currentTimeMillis() - startOfGame));
        game.getTopPanel().updateWaveLabel(currentWaveIndex, endWaveIndex);
        game.getTopPanel().updateXPLabel(Constant.getPlayerXP());
        game.getTopPanel().updateHPLabel(hp);

        if(!Constant.isqPressed())
            return;
        if(Constant.levelOfAttack == 1){
            game.getTopPanel().updateAbilityAttackIcon(imageIconWritOfAres);
        }
        else if(Constant.levelOfAttack == 2){
            game.getTopPanel().updateAbilityAttackIcon(imageIconWritOfAstrape);
        }
        else if(Constant.levelOfAttack >= 3){
            game.getTopPanel().updateAbilityAttackIcon(imageIconWritOfCerberus);
        }

        if(Constant.levelOfChangeShape == 1){
            game.getTopPanel().updateAbilityChangeShapeIcon(imageIconProteus);
        }
        else if(Constant.levelOfChangeShape == 2){
            game.getTopPanel().updateAbilityChangeShapeIcon(imageIconEmpusa);
        }
        else if(Constant.levelOfChangeShape >= 3){
            game.getTopPanel().updateAbilityChangeShapeIcon(imageIconDolus);
        }

        if(Constant.levelOfDefend == 1){
            game.getTopPanel().updateAbilityDefendIcon(imageIconAceso);
        }
        else if(Constant.levelOfAttack == 2){
            game.getTopPanel().updateAbilityDefendIcon(imageIconMelampus);
        }
        else if(Constant.levelOfAttack >= 3){
            game.getTopPanel().updateAbilityDefendIcon(imageIconChiron);
        }



    }
}
