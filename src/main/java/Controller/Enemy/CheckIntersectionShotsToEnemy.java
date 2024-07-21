package Controller.Enemy;

import Controller.Game.Intersection;
import Models.AttackType;
import Models.Enemy.Enemy;
import Models.EntityType;
import Models.Epsilon.Shot;
import Models.Game;

import java.util.ArrayList;

public class CheckIntersectionShotsToEnemy {
    public CheckIntersectionShotsToEnemy(){

    }
    public void checkIntersectionShotsToEnemy(Game game) {
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        for(Shot shot : game.getEpsilon().getShots()){
            for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
                if (Intersection.checkTheIntersectionBetweenAShotAndAObjectInGame(enemy, shot)) {
                    enemy.reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                    shot.getCurrentFrame().removeFromGamePanel(shot);
                    shotArrayList.add(shot);
                    break;
                }
            }
        }
        game.getEpsilon().getShots().removeAll(shotArrayList);
    }


}
