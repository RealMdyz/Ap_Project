package Controller;

import Models.Constant;
import Models.Enemy.EnemyWave;
import Models.Epsilon.Shot;
import Models.Game;
import Models.ObjectsInGame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class GameLoop extends Thread{


    private Game game;
    private Constant constant;
    private EnemyWave[] waves;
    private int currentWaveIndex;
    private int currentWaveIndexEnemy;

    private int delay = 0;

    public GameLoop(Game game){
        this.game = game;
        waves = new EnemyWave[3];
        waves[0] = new EnemyWave(3, 1000); // 3 enemies, 1 second delay
        waves[1] = new EnemyWave(5, 800);  // 5 enemies, 0.8 second delay
        waves[2] = new EnemyWave(7, 600);  // 7 enemies, 0.6 second delay
        currentWaveIndex = 0;
        currentWaveIndexEnemy = 0;

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
        delay += 10;
        if(delay >= waves[currentWaveIndex].getDelay()){
            addNewEnemy();
            System.out.println(currentWaveIndex);
            System.out.println(currentWaveIndexEnemy);
        }
        if(currentWaveIndex == 3){
            Constant.setIsRunning(false);
            JOptionPane.showMessageDialog(game.getGameFrame(), "Congratulations! You have defeated all waves.");
            System.exit(0);
        }
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
    private void addNewEnemy(){
        int index = 0;
        for(ObjectsInGame objects : waves[currentWaveIndex].getEnemies()){
            index += 1;
            game.getGameFrame().add(objects);
            if(index > currentWaveIndexEnemy){
                currentWaveIndexEnemy += 1;
                break;
            }
        }
        delay = 0;
        if(currentWaveIndexEnemy == waves[currentWaveIndex].getNumEnemies()){
            currentWaveIndexEnemy = 0;
            currentWaveIndex +=1;
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
