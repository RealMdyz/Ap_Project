package Controller;

import Models.Constant;
import Models.Game;

import java.awt.*;
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
        game.getGameFrame().getEpsilon().move();
        game.getGameFrame().repaint();
        try {
            Thread.sleep(10);
        }
        catch (Exception e){

        }
    }
}
