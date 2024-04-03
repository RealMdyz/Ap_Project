package Controller;

import Models.Constant;
import Models.Game;

import java.awt.*;
import java.util.Random;

public class GameLoop extends Thread{


    private Game game;
    private Constant constant;

    public GameLoop(Game game){
        this.game = game;
    }
    public void run() {


        while (game.isRunning()) {
            game.getStorePanel().setVisible(Constant.isOpenStore());
            if(!Constant.isOpenStore()){
                update();
            }
            else{
                store();
            }
        }


    }
    private void update(){
        game.getGameFrame().getEpsilon().move();
        game.getGameFrame().repaint();
        try {
            Thread.sleep(100);
        }
        catch (Exception e){

        }
    }
    private void store(){

        try {
            Thread.sleep(100);
        }
        catch (Exception e){

        }
    }
}
