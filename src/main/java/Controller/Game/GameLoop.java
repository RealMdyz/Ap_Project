package Controller.Game;

import Controller.BossFight.BossFightManger;
import Models.Constant;
import Models.Epsilon.Collectible;
import Models.Game;

import java.util.ArrayList;

public class GameLoop{
    private Game game;
    private Constant constant;
    public static long startOfGame = System.currentTimeMillis();
    private final Object lock = new Object();
    public GameLoop(Game game, Constant constant){
        this.constant = constant;
        this.game = game;


        EpsilonThread epsilonThread = new EpsilonThread();
        CalculatorThread calculatorThread = new CalculatorThread();
        EnemyThread enemyThread = new EnemyThread();
        GraphicThread graphicThread = new GraphicThread();
        GraphicThread1 graphicThread1 = new GraphicThread1();


        SmileyThread smileyMoverThread = new SmileyThread();

        calculatorThread.start();
        enemyThread.start();
        graphicThread.start();
        epsilonThread.start();
        graphicThread1.start();


        smileyMoverThread.start();

    }
    private class SmileyThread extends Thread{
        @Override
        public void run() {
            while (Constant.isIsRunning()){
                if(Constant.isBossTriggered()){
                    game.getBossFightManger().control(game);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    private class EpsilonThread extends Thread{
        @Override
        public void run() {
            while (Constant.isIsRunning()){
                if(!Constant.isOpenStore() && !Constant.isOpenCheckPointPanel()){
                    if(game.getBossFightManger().isFirstSpawnOfBossFight() || !Constant.isBossTriggered()){
                        game.getEpsilon().move();
                        game.getSkillTreeLogic().runSkills();
                    }
                    if(Constant.isBossTriggered() && game.getBossFightManger().isFirstSpawnOfBossFight()){
                        game.getEpsilon().getEpsilonController().handelEpsilonShotInBossFight(game);
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    private class GraphicThread extends Thread{
        @Override
        public void run() {
            while (Constant.isIsRunning()){
                if(!Constant.isOpenStore()){
                    game.getStorePanel().setVisible(false);
                    game.getCheckTheStateOfTheGame().allThing(game);
                    game.getEpsilon().getEpsilonController().setTheEpsilonFrameSize(game);
                    if(!Constant.isBossTriggered())
                        game.getEpsilon().getEpsilonController().fireShot(game);
                    game.getIntersectionController().getCheckIntersectionShotsToEnemy().checkIntersectionShotsToEnemy(game);
                }
                else{
                    if(System.currentTimeMillis() - Constant.getStartOFHypnosSlumber() > 10000)
                      game.getStorePanel().setVisible(true);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    private class GraphicThread1 extends Thread{
        @Override
        public void run() {
            while (Constant.isIsRunning()){
                if(!Constant.isOpenStore() && !Constant.isOpenCheckPointPanel()) {
                    game.getUpdateToPanel().updateTopPanel(startOfGame, game.getEnemyController().getCurrentWaveIndex(), Constant.NUMBER_OF_WAVE,game.getEpsilon().getHp());
                    game.getCollectibleController().checkTheExpirationTime();
                    game.getCollectibleController().checkTheIntersectionBetweenACollectibleAndEpsilon(game);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    private class EnemyThread extends Thread{
        @Override
        public void run() {
            while (!Constant.isBossTriggered() && Constant.isIsRunning()){
                if(!Constant.isOpenStore() && !Constant.isOpenCheckPointPanel() && System.currentTimeMillis() - Constant.getStartOFHypnosSlumber() > 10000) {
                    game.getEnemyController().controllingTheEnemies();
                    game.getArchmireAoeController().control(game);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    private class CalculatorThread extends Thread{
        @Override
        public void run() {
            while (!Constant.isBossTriggered() && Constant.isIsRunning()){
                if(!Constant.isOpenStore() && !Constant.isOpenCheckPointPanel()) {
                    game.getIntersectionController().controllingAllIntersections(game);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }





}