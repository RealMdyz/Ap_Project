package Controller;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.EnemyWave;
import Models.Epsilon.Shot;
import Models.Game;
import Models.ObjectsInGame;
import View.Menu.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class GameLoop extends Thread{


    private Game game;
    private Constant constant;
    private EnemyWave[] waves;
    private int currentWaveIndex;
    private int currentWaveIndexEnemy;
    private int delay = 0;
    private long startOfGame = 0;
    private boolean panelReduced = false;

    public GameLoop(Game game, Constant constant){
        this.constant = constant;
        this.game = game;
        waves = new EnemyWave[3];
        waves[0] = new EnemyWave(3, 2500); // 3 enemies, 1 second delay
        waves[1] = new EnemyWave(5, 800);  // 5 enemies, 0.8 second delay
        waves[2] = new EnemyWave(7, 600);  // 7 enemies, 0.6 second delay
        currentWaveIndex = 0;
        currentWaveIndexEnemy = 0;
        startOfGame = System.currentTimeMillis();
    }
    public void run() {

        // Start the timer for smooth size reduction
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
        updateTopPanel();
        shotMove();
        enemyMove();
        checkIntersection();
   //   checkTheImpact();
        delay += 15;
        if(delay >= waves[currentWaveIndex].getDelay()){
            addNewEnemy();
        }
        if(currentWaveIndex == 3){
            Constant.setIsRunning(false);
            JOptionPane.showMessageDialog(game.getGameFrame(), "Congratulations! You have defeated all waves.");
            System.exit(0);
        }
        try {
            Thread.sleep(20);
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
        if(currentWaveIndexEnemy >= waves[currentWaveIndex].getEnemies().size()){
            waves[currentWaveIndex].setWaveOver(true);
            for(ObjectsInGame objects : waves[currentWaveIndex].getEnemies()){
                if(objects.getHp() > 0){
                    waves[currentWaveIndex].setWaveOver(false);
                }
            }
            if(waves[currentWaveIndex].isWaveOver()){
                currentWaveIndex += 1;
                currentWaveIndexEnemy = 0;
            }
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

    private void enemyMove(){
        int index = 0;
        for(Enemy object: waves[currentWaveIndex].getEnemies()){
            index += 1;
            if(index > currentWaveIndexEnemy)
                break;;
            object.localRouting(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
            object.move();
            game.getGameFrame().repaint();
        }
    }
    private void checkIntersection(){
        int index = 0;
        Enemy enemy1 = new Enemy(0, 0, 1);
        Shot shot1 = new Shot(0,0 );
        for(Enemy enemy : waves[currentWaveIndex].getEnemies()){
            index += 1;
            if(index > currentWaveIndexEnemy)
                break;
            for(Shot shot : game.getGameFrame().getShots()){
                if(game.getIntersection().checkCollision(shot, enemy) && shot.getHp() == 1){
                    enemy.setHp(enemy.getHp() - 5);
                    shot.setHp(0);
                    shot1 = shot;
                    if(enemy.getHp() <= 0){
                        enemy1 = enemy;
                        game.getGameFrame().remove(enemy);
                        game.getGameFrame().revalidate();
                        game.getGameFrame().repaint();
                        currentWaveIndexEnemy -= 1;
                    }

                }
            }

        }
        waves[currentWaveIndex].getEnemies().remove(enemy1);
        game.getGameFrame().repaint();
        game.getGameFrame().removeOneShot(shot1);
    }
    private void checkTheImpact(){
        int index = 0;
        for(Enemy enemy : waves[currentWaveIndex].getEnemies()){
            index += 1;
            if(index > currentWaveIndexEnemy)
                break;
            int secondIndex = 0;
            for(Enemy enemy1 : waves[currentWaveIndex].getEnemies()){
                secondIndex += 1;
                if(secondIndex > currentWaveIndexEnemy)
                    break;
                if(secondIndex > index){
                    if(game.getIntersection().intersect(enemy1, enemy)){
                        doImpact(game.getIntersection().getIntersectionCenter(enemy, enemy1).x, game.getIntersection().getIntersectionCenter(enemy, enemy1).y);
                    }
                }
            }
        }
    }
    private void doImpact(int x, int y){
        for(Enemy enemy : waves[currentWaveIndex].getEnemies()){
            enemy.doImpact(x, y);

        }
        game.getGameFrame().getEpsilon().doImpact(x, y);
    }
    private void updateTopPanel(){
        game.getTopPanel().updateHPLabel(game.getGameFrame().getEpsilon().getHp());
        game.getTopPanel().updateXPLabel(constant.getPlayerXP());
        game.getTopPanel().updateTimeLabel((System.currentTimeMillis() - startOfGame));
        game.getTopPanel().updateWaveLabel(currentWaveIndex);
        if (!panelReduced && game.getGameFrame().getWidth() > 200 && game.getGameFrame().getHeight() > 200) {
            int newWidth = game.getGameFrame().getWidth() - 4;
            int newHeight = game.getGameFrame().getHeight() - 4;
            int x = (Toolkit.getDefaultToolkit().getScreenSize().width - newWidth) / 2;
            int y = (Toolkit.getDefaultToolkit().getScreenSize().height - newHeight) / 2;
            game.getGameFrame().setBounds(x, y, newWidth, newHeight);
            if (newWidth <= 400 && newHeight <= 400) {
                panelReduced = true; // Set the flag once the panel size is reduced
            }
        }
    }

}
