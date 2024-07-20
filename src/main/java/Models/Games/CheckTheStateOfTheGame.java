package Models.Games;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Game;
import View.Game.GameFrame;

import java.util.ArrayList;

public class CheckTheStateOfTheGame {
    public static  int haveACheckPoint = 0;
    public CheckTheStateOfTheGame(){

    }
    public void allThing(Game game){
        //saveGameData(game);
        checkTheState(game);
    }
    private void saveGameData(Game game){
        GameData.saveGame(game);
    }
    private void checkTheState(Game game){
        if(game.getEpsilon().getHp() <= 0 && haveACheckPoint == 0){
            gameEnd(game);
        }
        else if(game.getEpsilon().getHp() <= 0 && haveACheckPoint != 0){
            if(haveACheckPoint == 2){
                setTheStartState(game);
                game.getEnemyController().setCurrentWaveIndex(1);
            }
            else if(haveACheckPoint == 4){
                setTheStartState(game);
                game.getEnemyController().setCurrentWaveIndex(3);
            }
            haveACheckPoint = 0;
            game.getEpsilon().setHp(10);

        }
        if(game.getEnemyController().getCurrentWaveIndex() == 5){
            setTheStartState(game);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            game.getBossFightManger().trigger(game);
            Constant.setBossTriggered(true);
            game.getEnemyController().setCurrentWaveIndex(7);
            game.getEpsilon().getCurrentFrame().requestFocus();
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
            // GameData gameData = (GameData.loadGame());
            // System.out.println(gameData.epsilon.getX() + " " + gameData.epsilon.getY());
        }
    }
    private void setTheStartState(Game game){
        synchronized (this){
            for(GameFrame gameFrame : game.getGameFrames())
                gameFrame.setVisible(false);
            game.getEpsilon().getCurrentFrame().setVisible(true);
            game.getEpsilon().getCurrentFrame().setSize(400, 400);
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
            for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
                for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks()){
                    blackOrbChuck.getCurrentFrame().removeFromGamePanel(blackOrbChuck);
                }
            }
            game.getEnemyController().setEnemyArrayList(new ArrayList<>());
            game.getEnemyController().setBlackOrbs(new ArrayList<>());
            game.getEnemyController().setLastBlackOrbDone(false);
        }
    }

    public static int getHaveACheckPoint() {
        return haveACheckPoint;
    }

    public static void setHaveACheckPoint(int haveACheckPoint) {
        CheckTheStateOfTheGame.haveACheckPoint = haveACheckPoint;
    }
}
