package Models.Games;

import Models.Constant;
import Models.Enemy.Enemy;
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
        if(game.getEnemyController().getCurrentWaveIndex() == 5){
            Constant.setBossTriggered(true);
            setTheStartState(game);
            game.getEnemyController().setCurrentWaveIndex(7);
        }
    }
    private void gameEnd(Game game){
        synchronized (this){
            Constant.setIsRunning(false);
            for(GameFrame gameFrame : game.getGameFrames())
                gameFrame.setVisible(false);
            game.getMusicPlayer().stop();
            game.getEpsilonFrame().setVisible(false);
            Constant.setSavedXp(Constant.getPlayerXP() + Constant.getSavedXp());
        }
    }
    private void setTheStartState(Game game){
        synchronized (this){
            for(GameFrame gameFrame : game.getGameFrames())
                gameFrame.setVisible(false);
            game.getEpsilonFrame().setVisible(true);
            game.getEpsilon().setX(350);
            game.getEpsilon().setY(350);
            game.getEpsilonFrame().setSize(700, 700);
            game.getEpsilonFrame().setLocationRelativeTo(null);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                enemy.getCurrentFrame().removeFromGamePanel(enemy);
                enemy.removeTheImpactOnTheFrame();
            }

        }
    }
}
