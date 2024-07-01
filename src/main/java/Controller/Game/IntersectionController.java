package Controller.Game;

import Models.Enemy.*;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Game;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntersectionController {

    Intersection intersection;
    private List<Rectangle> aoeAreas;
    public IntersectionController(){
        intersection = new Intersection();
        aoeAreas = new ArrayList<>();
    }
    public void controllingAllIntersections(Game game){
        checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(game);
        checkIntersectionShotsToEpsilon(game);
        checkIntersectionShotsToEnemy(game);
        checkTheDrowmPowerOfArchmire(game);
    }

    public void checkTheDrowmPowerOfArchmire(Game game){
        for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
            if (enemy instanceof Archmire) {
                Archmire archmire = (Archmire) enemy;
                if (intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(game.getEpsilon(), archmire) &&System.currentTimeMillis() -  archmire.getLastDrownAttack() > 1000) {
                    game.getEpsilon().setHp(game.getEpsilon().getHp() - archmire.getDrownPower());
                    archmire.setLastDrownAttack(System.currentTimeMillis());
                }
            }
        }
    }
    public void checkIntersectionShotsToEnemy(Game game) {
        Iterator<Shot> shotIterator = game.getEpsilon().getShots().iterator();
        while (shotIterator.hasNext()) {
            Shot shot = shotIterator.next();
            for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
                if (intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(enemy, shot)) {
                    enemy.setHp(enemy.getHp() - game.getEpsilon().getPowerOfShot());
                    shot.getCurrentFrame().removeFromGamePanel(shot);
                    shotIterator.remove();

                    break;
                }
            }
        }
    }
    private void checkIntersectionShotsToEpsilon(Game game) {
        for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
            if (enemy instanceof Necropick) {
                handleShotIntersections(game, (Necropick) enemy);
            } else if (enemy instanceof Wyrm) {
                handleShotIntersections(game, (Wyrm) enemy);
            } else if (enemy instanceof Omenoct) {
                handleShotIntersections(game, (Omenoct) enemy);
            }
        }
    }

    private void handleShotIntersections(Game game, Necropick enemy) {
        Iterator<Shot> iterator = enemy.getShots().iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            if (intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)) {
                game.getEpsilon().setHp(game.getEpsilon().getHp() - enemy.getPower());
                iterator.remove();
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
    }

    private void handleShotIntersections(Game game, Wyrm enemy) {
        Iterator<Shot> iterator = enemy.getShots().iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            if (intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)) {
                game.getEpsilon().setHp(game.getEpsilon().getHp() - enemy.getPower());
                iterator.remove();
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
    }

    private void handleShotIntersections(Game game, Omenoct enemy) {
        Iterator<Shot> iterator = enemy.getShots().iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            if (intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)) {
                game.getEpsilon().setHp(game.getEpsilon().getHp() - enemy.getPower());
                iterator.remove();
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
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
