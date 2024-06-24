package Controller.Game;

import Models.Constant;
import Models.Game;

public class GameLoop{
    private Game game;
    private Constant constant;
    private long startOfGame = System.currentTimeMillis();
    private int indexWave;
    public GameLoop(Game game, Constant constant){
        this.constant = constant;
        this.game = game;

        EpsilonThread epsilonThread = new EpsilonThread();
        CalculatorThread calculatorThread = new CalculatorThread();
        EnemyThread enemyThread = new EnemyThread();
        GraphicThread graphicThread = new GraphicThread();
        calculatorThread.start();
        enemyThread.start();
        graphicThread.start();
        epsilonThread.start();


    }
    private class EpsilonThread extends Thread{
        @Override
        public void run() {
            while (!constant.isBossTriggered()){
                game.getEpsilon().move(game.getEpsilonFrame().getWidth(), game.getEpsilonFrame().getHeight());
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
            while (!constant.isBossTriggered()){

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
            while (!constant.isBossTriggered()){


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
            while (!constant.isBossTriggered()){


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }





}