package Models.Games;

import Models.Constant;
import Models.Game;
import View.Game.GameFrame;

public class CheckTheStateOfTheGame {

    boolean haveACheckPoint = false;
    public CheckTheStateOfTheGame(){

    }
    public void allThing(Game game){
        saveGameData(game);
        checkTheState(game);
    }
    private void saveGameData(Game game){

    }
    private void checkTheState(Game game){
        if(game.getEpsilon().getHp() <= 0 && !haveACheckPoint){
            gameEnd(game);
        }
    }
    private void gameEnd(Game game){
        synchronized (this){
            Constant.setIsRunning(false);
            for(GameFrame gameFrame : game.getGameFrames())
                gameFrame.setVisible(false);
            game.getMusicPlayer().stop();
            game.getEpsilonFrame().setVisible(false);
        }
    }
}
