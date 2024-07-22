package Controller.Game;

import Models.AttackType;
import Models.Enemy.Normal.Necropick;
import Models.Enemy.Normal.Omenoct;
import Models.Enemy.Normal.Wyrm;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Epsilon.Vertex;
import Models.Game;
import Models.Games.ImpactManager;
import Models.ObjectsInGame;

import java.util.ArrayList;

public class IntersectionHandler {
    private Intersection intersection;
    public IntersectionHandler(Intersection intersection){
        this.intersection = intersection;
    }

    public void handleShotIntersections(Game game, Necropick enemy) {
        for(Shot shot : enemy.getShots()){
            if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)){
                game.getEpsilon().reduceHp(shot.getPower(), AttackType.RANGED, EntityType.ENEMY);
                shot.setPower(0);
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
    }

    public void handleShotIntersections(Game game, Wyrm enemy) {
        for(Shot shot : enemy.getShots()){
            if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)){
                game.getEpsilon().reduceHp(shot.getPower(), AttackType.RANGED, EntityType.ENEMY);
                shot.setPower(0);
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
    }

    public void handleShotIntersections(Game game, Omenoct enemy) {
        for(Shot shot : enemy.getShots()){
            if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)){
                game.getEpsilon().reduceHp(shot.getPower(), AttackType.RANGED, EntityType.ENEMY);
                shot.setPower(0);
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
    }
    public boolean handleIntersectionVertexToObjectInGameForEnemiesVertexAndEpsilon(ArrayList<Vertex> vertices, Epsilon epsilon) {
        boolean c = false;
        for (Vertex vertex : vertices) {
            if (intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(vertex, epsilon) && System.currentTimeMillis() - vertex.getLastAttackFromMe() > 500) {
                epsilon.reduceHp(vertex.getPower(), AttackType.MELEE, EntityType.ENEMY);
                vertex.setLastAttackFromMe(System.currentTimeMillis());
                ImpactManager.checkAndDoingTheImpact(epsilon, vertex.getX(), vertex.getY());
                c = true;
            }
        }
        return c;
    }




}
