package Models.Games;

import Controller.Enemy.EnemyController;
import Controller.Game.GameLoop;
import Models.BossFight.SmileyFace;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Epsilon.Epsilon;
import Models.Game;
import MyProject.MyProjectData;
import View.Game.GameFrame;
import View.Menu.GameOverPanel;

import java.util.ArrayList;

public class CheckTheStateOfTheGame {
    public static  int haveACheckPoint = 0;
    public CheckTheStateOfTheGame(){

    }
    public void allThing(Game game){
        saveGameData(game);
        checkTheState(game);
    }
    private void saveGameData(Game game){
        game.getCreateFromConfig().createLevelConfig(game);
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
            game.getEnemyController().setCurrentWaveIndex(7);
            setTheStartState(game);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            game.getBossFightManger().trigger(game);
            Constant.setBossTriggered(true);
            game.getEpsilon().getCurrentFrame().requestFocus();
        }
        if(Constant.isBossTriggered() && game.getBossFightManger().getSmileyFace().getHp() <= 0){
            SmileyFace smileyFace = game.getBossFightManger().getSmileyFace();
            smileyFace.setBackground(MyProjectData.getProjectData().getHeadSkeleton());
            Epsilon epsilon = game.getEpsilon();
            smileyFace.setHeight(smileyFace.getHeight() - 1);
            smileyFace.setWidth(smileyFace.getWidth() - 1);
            smileyFace.repaint();
            epsilon.setHeight(epsilon.getHeight() + 3);
            epsilon.setWidth(epsilon.getWidth() + 3);
            epsilon.repaint();
            if(smileyFace.getHeight() == 0){
                GameOverPanel gameOverPanel = new GameOverPanel(epsilon.getEpsilonController().numberOfShot, Epsilon.successfulShots, EnemyController.enemiesKilled, Constant.getPlayerXP(), System.currentTimeMillis() - GameLoop.startOfGame);
                gameOverPanel.setVisible(true);
                gameEnd(game);
            }
        }
    }
    private void gameEnd(Game game){
        synchronized (this){
            Constant.setIsRunning(false);
            for(GameFrame gameFrame : game.getGameFrames())
                gameFrame.setVisible(false);
            game.getMusicPlayer().stop();
            game.getEpsilonFrame().setVisible(false);
            game.getConstant().updateToSkillAndXp();
            Constant.setSavedXp(Constant.getPlayerXP() + Constant.getSavedXp());
            game.getConstant().writeInFile(Constant.getSavedXp(), Constant.levelOfAttack, Constant.levelOfDefend, Constant.levelOfChangeShape);
            Constant.setBossTriggered(false);
            Constant.setqPressed(false);
            Constant.setPlayerXP(0);
            

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
        }
    }

    public static int getHaveACheckPoint() {
        return haveACheckPoint;
    }

    public static void setHaveACheckPoint(int haveACheckPoint) {
        CheckTheStateOfTheGame.haveACheckPoint = haveACheckPoint;
    }
}
