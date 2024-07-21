package Controller.Game;

import Controller.Enemy.CheckIntersectionShotsToEnemy;
import Controller.Enemy.NormalEnemyPowerIntersection;
import Models.AttackType;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.Barricados.Barricados;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Enemy.Normal.*;
import Models.EntityType;
import Models.Epsilon.Shot;
import Models.Game;
import Models.Games.CheckTheStateOfTheGame;
import View.Game.GameFrame;

import java.util.ArrayList;
import java.util.List;

public class IntersectionController {

    Intersection intersection;
    IntersectionHandler intersectionHandler;
    CheckIntersectionShotsToEnemy checkIntersectionShotsToEnemy;
    NormalEnemyPowerIntersection normalEnemyPowerIntersection;
    public IntersectionController(){
        intersection = new Intersection();
        intersectionHandler = new IntersectionHandler(intersection);
        checkIntersectionShotsToEnemy = new CheckIntersectionShotsToEnemy();
        normalEnemyPowerIntersection = new NormalEnemyPowerIntersection();
    }
    public void controllingAllIntersections(Game game){
        checkTheAddingAndRemovingTheEnemiesFromTheFramesToEpsilonFrame(game);
        checkIntersectionShotsToEpsilonAndOtherDataIntersectionOfNormalEnemies(game);
        checkTheDrowmPowerOfArchmire(game);
        checkTheIntersectionBetweenBarricadosFrameAndWyrmFrame(game);
        checkTheLinePowerOfBlackOrbChunks(game);
        checkTheCheckPointIntersection(game);
        checkTheEpsilonShotIntersectionToHisFrame(game);
        checkTheIntersectionForBlackOrb(game);
    }
    public void checkTheEpsilonShotIntersectionToHisFrame(Game game){
        ArrayList<Shot> shotsToRemove = new ArrayList<>();
        List<Shot> shotsCopy;

        synchronized (game.getEpsilon().getShots()) {
            shotsCopy = new ArrayList<>(game.getEpsilon().getShots());
            for (Shot shot : shotsCopy) {
                shot.move();
                shot.repaint();
                shot.getCurrentFrame().repaint();
                if (game.getEpsilon().getEpsilonController().isCollidingWithFrameEdge(shot, game.getEpsilonFrame())) {
                    game.getEpsilon().getEpsilonController().enlargeEpsilonFrame(game, shot);
                    shot.getCurrentFrame().removeFromGamePanel(shot);
                    shotsToRemove.add(shot);
                }
            }

            // Remove identified shots from the original list
            game.getEpsilon().getShots().removeAll(shotsToRemove);
        }

        checkTheIntersectionToTheWallForReduceTheOmenoctHp(game, shotsToRemove);
    }
    public void checkTheIntersectionToTheWallForReduceTheOmenoctHp(Game game, ArrayList<Shot> shots){
        for(Shot shot : shots){
            if(shot.getX() <= 0){
                for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                    if(enemy instanceof  Omenoct){
                        Omenoct omenoct = (Omenoct) enemy;
                        if(omenoct.getSide() == 2){
                            omenoct.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                        }
                    }
                }
            }
            else if(shot.getY() <= 0){
                for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                    if(enemy instanceof  Omenoct){
                        Omenoct omenoct = (Omenoct) enemy;
                        if(omenoct.getSide() == 0){
                            omenoct.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                        }
                    }
                }
            }
            else if(shot.getY() >= shot.getCurrentFrame().getHeight() - shot.getHeight()){
                for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                    if(enemy instanceof  Omenoct){
                        Omenoct omenoct = (Omenoct) enemy;
                        if(omenoct.getSide() == 1){
                            omenoct.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                        }
                    }
                }
            }
            else{
                for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                    if(enemy instanceof  Omenoct){
                        Omenoct omenoct = (Omenoct) enemy;
                        if(omenoct.getSide() == 3){
                            omenoct.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                        }
                    }
                }
            }

        }
    }
    public void checkTheIntersectionForBlackOrb(Game game){

        if(!game.getEnemyController().isLastBlackOrbDone())
            return;

        Shot shot1 = new Shot(0, 0, 0, game.getEpsilon().getCurrentFrame(), false);
        for(Shot shot : game.getEpsilon().getShots()){
            for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
                for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks()){
                    if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(blackOrbChuck, shot)){
                        blackOrbChuck.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                        shot1 = shot;
                        break;
                    }
                }
            }
        }
        game.getEpsilon().getCurrentFrame().removeFromGamePanel(shot1);
        game.getEpsilon().getShots().remove(shot1);

    }
    public void checkTheCheckPointIntersection(Game game){
        if(game.getEnemyController().getCurrentWaveIndex() == 1 && game.getCheckPointController().wave2CheckPoint != null && intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(game.getEpsilon(), game.getCheckPointController().wave2CheckPoint)){
            game.getCheckPointController().checkPointStart(game);
            CheckTheStateOfTheGame.haveACheckPoint = 2;
            game.getCheckPointController().wave2CheckPoint = null;
        }
        else if(game.getEnemyController().getCurrentWaveIndex() == 3 && game.getCheckPointController().wave4CheckPoint != null && intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(game.getEpsilon(), game.getCheckPointController().wave4CheckPoint)){
            game.getCheckPointController().checkPointStart(game);
            CheckTheStateOfTheGame.haveACheckPoint = 4;
            game.getCheckPointController().wave4CheckPoint = null;

        }

        if(game.getEnemyController().getCurrentWaveIndex() > 1 &&  game.getCheckPointController().wave2CheckPoint != null){
            game.getCheckPointController().wave2CheckPoint.setVisible(false);
        }
        if(game.getEnemyController().getCurrentWaveIndex() > 3 &&  game.getCheckPointController().wave4CheckPoint != null){
            game.getCheckPointController().wave4CheckPoint.setVisible(false);

        }

    }
    public void checkTheLinePowerOfBlackOrbChunks(Game game){
        for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
            if(blackOrb.betweenTwoOfMyChunks(game.getEpsilon())){
                game.getEpsilon().reduceHp(BlackOrb.LINE_POWER_OF_BLACK_ORB, AttackType.LASER, EntityType.ENEMY);
            }
            for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                if(blackOrb.betweenTwoOfMyChunks(enemy)){
                    enemy.reduceHp(BlackOrb.LINE_POWER_OF_BLACK_ORB, AttackType.LASER, EntityType.ENEMY);
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
                if(intersectionHandler.handleIntersectionVertexToObjectInGameForEnemiesVertexAndEpsilon(omenoct.getVertices(), game.getEpsilon())){
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
            if(intersection.isInThisFrame(enemy, gameFrame) && !enemy.getCurrentFrame().equals(gameFrame) && !(enemy instanceof Barricados)){
                enemy.getCurrentFrame().setVisible(false);
                game.getGameFrames().remove(enemy.getCurrentFrame());
                enemy.changeFrameAndPaint(gameFrame);
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
                    blackOrb.reDrawLasers();
                    blackOrb.rePoint();
                }
            }
        }

    }

    public CheckIntersectionShotsToEnemy getCheckIntersectionShotsToEnemy() {
        return checkIntersectionShotsToEnemy;
    }

    public void setCheckIntersectionShotsToEnemy(CheckIntersectionShotsToEnemy checkIntersectionShotsToEnemy) {
        this.checkIntersectionShotsToEnemy = checkIntersectionShotsToEnemy;
    }
}
