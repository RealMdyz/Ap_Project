package Controller.Enemy;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Enemy.Normal.Omenoct;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Game;

import java.util.ArrayList;
import java.util.List;

public class HandelEpsilonShots {
    public HandelEpsilonShots(){

    }
    public void checkIntersectionShotsToEnemy(Game game) {
        for(Shot shot : game.getEpsilon().getShots()){
            for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
                if (shot.getPower() != 0 && Intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(enemy, shot)) {
                    enemy.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                    shot.getCurrentFrame().removeFromGamePanel(shot);
                    shot.setPower(0);
                    Epsilon.successfulShots += 1;
                    break;
                }
            }
        }

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
                    if(enemy instanceof Omenoct){
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
                    if(Intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(blackOrbChuck, shot)){
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


}
