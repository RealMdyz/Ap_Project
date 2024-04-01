package Controller;

import Models.Game;

import java.util.Random;

public class GameLoop extends Thread{


    private Game game;

    public GameLoop(Game game){
        this.game = game;
    }
    public void run() {
        while (game.isRunning()) {
            update();
        }
    }
    private void update(){
        Random random = new Random();
        game.getGameFrame().setBounds(random.nextInt(700), random.nextInt(700), random.nextInt(700) + 50, random.nextInt(700) + 50);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
