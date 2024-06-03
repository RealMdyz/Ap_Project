package Controller.Enemy;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Game;

import java.util.ArrayList;
import java.util.Collections;

public class EnemyController {
    private Game game;
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    public EnemyController(Game game){
        this.game = game;
    }
    public void enemyMove(){
        for(Enemy enemy : enemyArrayList){
            enemy.move(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());
            game.getGameFrame().repaint();
        }
    }
    public void addEnemy(Enemy enemy){
        enemyArrayList.add(enemy);
        game.getGameFrame().add(enemy);
    }

    public ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    public void setEnemyArrayList(ArrayList<Enemy> enemyArrayList) {
        this.enemyArrayList = enemyArrayList;
    }
}
