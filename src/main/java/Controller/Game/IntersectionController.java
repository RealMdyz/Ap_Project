package Controller.Game;

import Models.Enemy.Enemy;
import Models.Game;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class IntersectionController {

    public IntersectionController(){

    }
    public void controllingAllIntersections(Game game){
        checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(game);

    }
    private void checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(Game game){
        GameFrame gameFrame = game.getEpsilonFrame();
        for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
            if(isInThisFrame(enemy, gameFrame) && !enemy.getCurrentFrame().equals(gameFrame)){
                gameFrame.addToGamePanel(enemy);
                int xNesbatWindow = enemy.getX() + enemy.getCurrentFrame().getX();
                int yNesbatWindow = enemy.getY() + enemy.getCurrentFrame().getY();
                enemy.setX(xNesbatWindow - gameFrame.getX());
                enemy.setY(yNesbatWindow - gameFrame.getY());
                enemy.getCurrentFrame().setVisible(false);
                enemy.getCurrentFrame().removeFromGamePanel(enemy);
                enemy.setCurrentFrame(gameFrame);

            }

        }

    }
    public boolean isInThisFrame(Enemy enemy, GameFrame gameFrame){
        int xNesbatBeWindowOfEnemy = enemy.getX() + enemy.getCurrentFrame().getX();
        int yNesbatBeWindowOfEnemy = enemy.getY() + enemy.getCurrentFrame().getY();
        int widthEnemy = enemy.getWidth();
        int heightEnemy = enemy.getHeight();
        int xGameFrame = gameFrame.getX();
        int yGameFrame = gameFrame.getY();
        int widthGameFrame = gameFrame.getWidth();
        int heightGameFrame = gameFrame.getHeight();
        Rectangle enemyBounds = new Rectangle(xNesbatBeWindowOfEnemy, yNesbatBeWindowOfEnemy, widthEnemy, heightEnemy);
        Rectangle frameBounds = new Rectangle(xGameFrame, yGameFrame, widthGameFrame, heightGameFrame);

        return enemyBounds.intersects(frameBounds);
    }
}
