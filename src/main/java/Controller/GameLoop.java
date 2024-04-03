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
            game.getGameFrame().getGamePanel().add(shot);
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
        Shot removedShot = new Shot(0, 0);
        int cnt = 0;
        for(Shot shot : game.getGameFrame().getShots()){
            shot.move();
            if(shot.getX() <= -40){
                game.getGameFrame().setBounds(game.getGameFrame().getX() - 6, game.getGameFrame().getY(), game.getGameFrame().getWidth() + 2, game.getGameFrame().getHeight());
                removedShot = shot;
                cnt = 1;
            }

            if(shot.getX() >= game.getGameFrame().getWidth()){
                game.getGameFrame().setBounds(game.getGameFrame().getX() + 2, game.getGameFrame().getY(), game.getGameFrame().getWidth() + 2, game.getGameFrame().getHeight());
                removedShot = shot;
                cnt = 1;
            }
            if(shot.getY() <= -40){
                game.getGameFrame().setBounds(game.getGameFrame().getX(), game.getGameFrame().getY() - 6, game.getGameFrame().getWidth(), game.getGameFrame().getHeight() + 2);
                removedShot = shot;
                cnt = 1;
            }
            if(shot.getY() >= game.getGameFrame().getHeight()){
                game.getGameFrame().setBounds(game.getGameFrame().getX(), game.getGameFrame().getY() + 2, game.getGameFrame().getWidth(), game.getGameFrame().getHeight() + 2);
                removedShot = shot;
                cnt = 1;
            }
            game.getGameFrame().repaint();
        }
        if(cnt == 1)
            game.getGameFrame().removeOneShot(removedShot);

    }
}
