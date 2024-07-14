package Controller.Game;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.Barricados.Barricados;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Enemy.Normal.*;
import Models.Epsilon.Collectible;
import Models.Epsilon.Shot;
import Models.Game;
import View.Game.GameFrame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntersectionController {

    Intersection intersection;
    IntersectionHandler intersectionHandler;
    public IntersectionController(){
        intersection = new Intersection();
        intersectionHandler = new IntersectionHandler(intersection);
    }
    public void controllingAllIntersections(Game game){
        checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(game);
        checkIntersectionShotsToEpsilonAndOtherDataIntersectionOfNormalEnemies(game);
        checkIntersectionShotsToEnemy(game);
        checkTheDrowmPowerOfArchmire(game);
        checkTheIntersectionBetweenACollectibleAndEpsilon(game);
        checkTheIntersectionBetweenBarricadosFrameAndWyrmFrame(game);
        checkTheLinePowerOfBlackOrbChunks(game);
        checkTheCheckPointIntersection(game);
    }
    public void checkTheCheckPointIntersection(Game game){
        if(game.getEnemyController().getCurrentWaveIndex() == 1 && game.getCheckPointController().wave2CheckPoint != null && intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(game.getEpsilon(), game.getCheckPointController().wave2CheckPoint)){
            game.getCheckPointController().checkPointStart(game);
        }
        else if(game.getEnemyController().getCurrentWaveIndex() == 3 && game.getCheckPointController().wave4CheckPoint != null && intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(game.getEpsilon(), game.getCheckPointController().wave4CheckPoint)){
            game.getCheckPointController().checkPointStart(game);
        }
    }
    public void checkTheLinePowerOfBlackOrbChunks(Game game){

        for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
            if(blackOrb.betweenTwoOfMyChunks(game.getEpsilon())){
                game.getEpsilon().reduceHp(BlackOrb.LINE_POWER_OF_BLACK_ORB);
            }
            for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                if(blackOrb.betweenTwoOfMyChunks(enemy)){
                    enemy.reduceHp(BlackOrb.LINE_POWER_OF_BLACK_ORB);
                }
            }

        }
    }
    public void checkTheIntersectionBetweenBarricadosFrameAndWyrmFrame(Game game) {
        List<Enemy> enemies = game.getEnemyController().getEnemyArrayList();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Wyrm) {
                Wyrm wyrm = (Wyrm) enemy;
                boolean c = true;
                for (Enemy otherEnemy : enemies) {
                    if (otherEnemy instanceof Barricados) {
                        Barricados barricados = (Barricados) otherEnemy;
                        if (FrameIntersection.twoFrameIntersection(barricados.getCurrentFrame(), wyrm.getCurrentFrame())) {
                            c = false;
                            break;
                        }
                    }
                }
                wyrm.setMovingTowardsEpsilon(c);
            }

        }
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
        List<Shot> shotsToRemove = new ArrayList<>();
        synchronized (game.getEnemyController().getEnemyArrayList()) {
            Iterator<Shot> shotIterator = game.getEpsilon().getShots().iterator();

            while (shotIterator.hasNext()) {
                Shot shot = shotIterator.next();
                synchronized (game.getEnemyController().getEnemyArrayList()) {
                    for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
                        if (intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(enemy, shot)) {
                            enemy.setHp(enemy.getHp() - shot.getPower());
                            shot.getCurrentFrame().removeFromGamePanel(shot);
                            shotsToRemove.add(shot);
                            break;
                        }
                    }
                }
            }
        }

        synchronized (game.getEpsilon().getShots()) {
            game.getEpsilon().getShots().removeAll(shotsToRemove);
        }
    }

    private void checkIntersectionShotsToEpsilonAndOtherDataIntersectionOfNormalEnemies(Game game) {
        for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
            if (enemy instanceof Necropick) {
                intersectionHandler.handleShotIntersections(game, (Necropick) enemy);
            } else if (enemy instanceof Wyrm) {
                Wyrm wyrm = (Wyrm) enemy;
                intersectionHandler.handleShotIntersections(game, (Wyrm) enemy);
                for(Enemy enemy1 : game.getEnemyController().getEnemyArrayList()){
                    if(enemy.getCurrentFrame().equals(enemy1) && intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(enemy1, wyrm) && !enemy1.equals(wyrm)){
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
            else if(enemy instanceof Archmire){
                Archmire archmire = (Archmire) enemy;
                for(Enemy enemy1 : game.getEnemyController().getEnemyArrayList()){
                    if(!enemy1.equals(enemy))
                         archmire.checkAoEDamage(enemy1);
                }
                archmire.checkAoEDamage(game.getEpsilon());
            }
        }
    }

    private void checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(Game game){
        GameFrame gameFrame = game.getEpsilonFrame();
        for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
            if(intersection.isInThisFrame(enemy, gameFrame) && !enemy.getCurrentFrame().equals(gameFrame)){
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
        for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
            for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks()){
                if (!blackOrbChuck.getCurrentFrame().equals(gameFrame) && intersection.isInThisFrame(blackOrbChuck, game.getEpsilon().getCurrentFrame())){
                    gameFrame.addToGamePanel(blackOrbChuck);
                    int xNesbatWindow = blackOrbChuck.getX() + blackOrbChuck.getCurrentFrame().getX();
                    int yNesbatWindow = blackOrbChuck.getY() + blackOrbChuck.getCurrentFrame().getY();
                    blackOrbChuck.setX(xNesbatWindow - gameFrame.getX());
                    blackOrbChuck.setY(yNesbatWindow - gameFrame.getY());
                    blackOrbChuck.getCurrentFrame().setVisible(false);
                    blackOrbChuck.getCurrentFrame().removeFromGamePanel(blackOrbChuck);
                    blackOrbChuck.setCurrentFrame(gameFrame);
                    System.out.println("Hello1");
                    blackOrb.reDrawLasers();
                    blackOrb.rePoint();
                }
            }
        }

    }


}
