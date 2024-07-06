package Controller.Game;

import Models.Enemy.Enemy;
import Models.Enemy.Necropick;
import Models.Enemy.Omenoct;
import Models.Enemy.Wyrm;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Game;

import java.security.spec.ECPoint;
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

}
