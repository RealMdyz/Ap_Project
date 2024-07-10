package Controller.Game;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.Normal.*;
import Models.Epsilon.Collectible;
import Models.Epsilon.Shot;
import Models.Game;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntersectionController {

    Intersection intersection;
    IntersectionHandler intersectionHandler;
    private List<Rectangle> aoeAreas;
    public IntersectionController(){
        intersection = new Intersection();
        //aoeAreas = new ArrayList<>();
        intersectionHandler = new IntersectionHandler(intersection);
    }
    public void controllingAllIntersections(Game game){
        checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(game);
        checkIntersectionShotsToEpsilonAndOtherDataIntersectionOfNormalEnemies(game);
        checkIntersectionShotsToEnemy(game);
        checkTheDrowmPowerOfArchmire(game);
        checkTheIntersectionBetweenACollectibleAndEpsilon(game);
    }
    public void checkTheIntersectionBetweenACollectibleAndEpsilon(Game game){
        ArrayList<Collectible> collectibleArrayList = new ArrayList<>();
        for(Collectible collectible : game.getCollectibleController().collectibles){
            if(intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(game.getEpsilon(), collectible)){
                game.getCollectibleController().intersectionHappen(collectible);
                collectibleArrayList.add(collectible);
                Constant.setPlayerXP(Constant.getPlayerXP() + collectible.getIncreaseXp());
            }
        }
        game.getCollectibleController().getCollectibles().removeAll(collectibleArrayList);
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
        ArrayList<Shot> shotsToRemove = new ArrayList<>();
        for (Shot shot : game.getEpsilon().getShots()) {
            for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
                if (intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(enemy, shot)) {
                    enemy.setHp(enemy.getHp() - game.getEpsilon().getPowerOfShot());
                    shot.getCurrentFrame().removeFromGamePanel(shot);
                    shotsToRemove.add(shot);
                    break;
                }
            }
        }
        game.getEpsilon().getShots().removeAll(shotsToRemove);
    }
    private void checkIntersectionShotsToEpsilonAndOtherDataIntersectionOfNormalEnemies(Game game) {
        for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
            if (enemy instanceof Necropick) {
                intersectionHandler.handleShotIntersections(game, (Necropick) enemy);
            } else if (enemy instanceof Wyrm) {
                Wyrm wyrm = (Wyrm) enemy;
                intersectionHandler.handleShotIntersections(game, (Wyrm) enemy);
                for(Enemy enemy1 : game.getEnemyController().getEnemyArrayList()){
                    if(intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(enemy1, wyrm) && !enemy1.equals(wyrm)){
                        wyrm.changingClockwiseOrCounterClockwise();
                    }
                }
            } else if (enemy instanceof Omenoct) {
                Omenoct omenoct = (Omenoct) enemy;
                intersectionHandler.handleShotIntersections(game, (Omenoct) enemy);
                if(intersectionHandler.handleIntersectionVertexToObjectInGame(omenoct.getVertices(), game.getEpsilon())){
                    omenoct.changeSide();
                }
                for(Enemy enemy1 : game.getEnemyController().getEnemyArrayList()){
                    if(intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(enemy1, omenoct) && !enemy1.equals(omenoct)){
                        omenoct.changeSide();
                    }
                }
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
