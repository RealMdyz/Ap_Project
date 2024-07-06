package Controller.Game;

import Models.Constant;
import Models.Epsilon.Collectible;
import Models.Game;

import java.util.ArrayList;

public class GameLoop{
    private Game game;
    private Constant constant;
    private long startOfGame = System.currentTimeMillis();
    private int indexWave;
    private int xp = 0;
    public GameLoop(Game game, Constant constant){
        this.constant = constant;
        this.game = game;


        EpsilonThread epsilonThread = new EpsilonThread();
        CalculatorThread calculatorThread = new CalculatorThread();
        EnemyThread enemyThread = new EnemyThread();
        GraphicThread graphicThread = new GraphicThread();
        GraphicThread1 graphicThread1 = new GraphicThread1();

        calculatorThread.start();
        enemyThread.start();
        graphicThread.start();
        epsilonThread.start();
        graphicThread1.start();

    }
    private class EpsilonThread extends Thread{
        @Override
        public void run() {
            while (!constant.isBossTriggered() && Constant.isIsRunning()){
                game.getEpsilon().move();
                game.getEpsilon().specialPower(game);
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
            while (!constant.isBossTriggered() && Constant.isIsRunning()){

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
            while (!constant.isBossTriggered() && Constant.isIsRunning()){
                game.getEnemyController().controllingTheEnemies();

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
            while (!constant.isBossTriggered() && Constant.isIsRunning()){
                game.getIntersectionController().controllingAllIntersections(game);

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
            while (!constant.isBossTriggered()){
                game.getUpdateToPanel().updateTopPanel(startOfGame, indexWave, Constant.NUMBER_OF_WAVE, xp, game.getEpsilon().getHp());
                game.getCollectibleController().checkTheExpirationTime();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }





}