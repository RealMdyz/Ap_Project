package Controller.Enemy;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.Necropick;
import Models.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EnemyController {
    private Game game;
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private int currentWaveIndex = 0;
    private int currentWaveEnemyDied = 0;
    private WaveController waveController;
    private MakeEnemy makeEnemy;
    public EnemyController(Game game){
        this.game = game;
        waveController = new WaveController();
        makeEnemy = new MakeEnemy();
    }
    public void enemyMove(){

    }
    public void addEnemy(Enemy enemy){

    }
    public void checkNecropickEsp(){
        for(Enemy enemy : enemyArrayList){
            if (enemy instanceof Necropick) {
                Necropick necropick = (Necropick) enemy;
                necropick.checkEsp(game);
            }
        }
    }
    public void spawnProcess(){
        if(currentWaveEnemyDied >= waveController.getNumberOfEnemyInEachWave()[currentWaveIndex]){

        }
        else if(waveController.getCurrentDelay() > (5 - currentWaveIndex) * 100){
            waveController.setCurrentDelay(0);
            Random random = new Random();
            addEnemy(makeEnemy.makeRandomEnemy(random.nextInt(), currentWaveIndex));
        }
        waveController.setCurrentDelay((int) (waveController.getCurrentDelay() + 10));
    }

    public ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    public void setEnemyArrayList(ArrayList<Enemy> enemyArrayList) {
        this.enemyArrayList = enemyArrayList;
    }
}