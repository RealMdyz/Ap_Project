package Controller.Game;

import Models.Enemy.Normal.Necropick;
import Models.Enemy.Normal.Omenoct;
import Models.Enemy.Normal.Wyrm;
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
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        for(Shot shot : enemy.getShots()){
            if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)){
                game.getEpsilon().setHp(game.getEpsilon().getHp() - enemy.getPower());
                shotArrayList.add(shot);
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
        enemy.getShots().removeAll(shotArrayList);
    }

    public void handleShotIntersections(Game game, Wyrm enemy) {
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        for(Shot shot : enemy.getShots()){
            if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)){
                game.getEpsilon().setHp(game.getEpsilon().getHp() - enemy.getPower());
                shotArrayList.add(shot);
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
        enemy.getShots().removeAll(shotArrayList);
    }

    public void handleShotIntersections(Game game, Omenoct enemy) {
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        for(Shot shot : enemy.getShots()){
            if(intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(game.getEpsilon(), shot)){
                game.getEpsilon().setHp(game.getEpsilon().getHp() - enemy.getPower());
                shotArrayList.add(shot);
                enemy.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
        enemy.getShots().removeAll(shotArrayList);
    }
    public boolean handleIntersectionVertexToObjectInGame(ArrayList<Vertex> vertices, ObjectsInGame object) {
        boolean c = false;
        for (Vertex vertex : vertices) {
            if (intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(vertex, object) && System.currentTimeMillis() - vertex.getLastAttackFromMe() > 100) {
                object.setHp(object.getHp() - vertex.getPower());
                vertex.setLastAttackFromMe(System.currentTimeMillis());
                ImpactManager.checkAndDoingTheImpact(object, vertex.getX(), vertex.getY());
                c = true;
            }
        }
        return c;
    }




}
