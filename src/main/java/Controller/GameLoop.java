package Controller;

import Models.Constant;
import Models.Epsilon.Shot;
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


        while (Constant.isIsRunning()) {
            game.getStorePanel().setVisible(Constant.isOpenStore());
            if(!Constant.isOpenStore()){
                update();
            }
            else{
                store();
            }
        }
        game.getGameFrame().setVisible(false);

    }
    private void update(){
        game.getGameFrame().getEpsilon().move();
        game.getGameFrame().repaint();
        if(game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1){
            Shot shot = new Shot(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
            shot.setV(game.getInputListener().getxMousePress(), game.getInputListener().getyMousePress());
            game.getGameFrame().getShots().add(shot);
            game.getGameFrame().add(shot);
            game.getInputListener().setxMousePress(-1);
            game.getInputListener().setyMousePress(-1);
        }
        shotMove();
        try {
            Thread.sleep(10);
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
    private void shotMove(){
        for(Shot shot : game.getGameFrame().getShots()){
            shot.move();
            game.getGameFrame().repaint();
        }
    }
}
