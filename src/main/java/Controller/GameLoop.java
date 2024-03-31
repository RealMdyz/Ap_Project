package Controller;

import Models.Game;

public class GameLoop extends Thread{


    private Game game;

    public GameLoop(Game game){
        this.game = game;
    }
    public void run() {
        while (game.isRunning) {
            update();
        }
    }
    private void update(){
        System.out.println("Hello");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
