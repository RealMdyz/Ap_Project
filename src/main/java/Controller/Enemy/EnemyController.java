package Controller.Enemy;

import Models.Enemy.Enemy;
import Models.Enemy.MiniBos.Barricados.Barricados;
import Models.Enemy.MiniBos.BlackOrb.BlackOrb;
import Models.Enemy.Normal.Necropick;
import Models.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class EnemyController {
    private Game game;
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
    private boolean lastBlackOrbDone = true;
    private int currentWaveIndex = 0;
    private int currentWaveEnemyDied = 0;

    private WaveController waveController;
    private MakeEnemy makeEnemy;

    public EnemyController(Game game){
        this.game = game;
        waveController = new WaveController();
        makeEnemy = new MakeEnemy(game);
    }
    public void controllingTheEnemies(){
        spawnProcess();
        blackOrbSpawnProcess();
        for(Enemy enemy : enemyArrayList){
            enemy.specialPowers(game.getEpsilon());
            enemy.move();
        }
        removingEnemies();
    }
    public void blackOrbSpawnProcess(){
        if(lastBlackOrbDone && Math.random() < 0.05 && blackOrbs.size() == 0){
            int x = 1000 + new Random().nextInt() % 30 + Math.abs(new Random().nextInt() % 180);
            int y = 300 + new Random().nextInt() % 30 +  Math.abs(new Random().nextInt() % 180);
            BlackOrb blackOrb = new BlackOrb(x, y);
            blackOrbs.add(blackOrb);
            lastBlackOrbDone = false;
        }
        else if(!lastBlackOrbDone && System.currentTimeMillis() - blackOrbs.get(blackOrbs.size() - 1).getLastChuckSpawnTime() > BlackOrb.SPAWN_TIME){
            BlackOrb blackOrb = blackOrbs.get(blackOrbs.size() - 1);
            blackOrb.spawnAChuck(game);
            blackOrb.setLastChuckSpawnTime(System.currentTimeMillis());
            if(blackOrb.getSpawnChuck() == 5)
                lastBlackOrbDone = true;
        }
    }
    public void addEnemy(Enemy enemy){
        enemy.getCurrentFrame().addToGamePanel(enemy);
        enemyArrayList.add(enemy);
    }
    public void removingEnemies() {
        Iterator<Enemy> iterator = enemyArrayList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.getHp() <= 0) {
                enemy.getCurrentFrame().removeFromGamePanel(enemy);
                enemy.removeTheImpactOnTheFrame();
                game.getCollectibleController().addingCollectiblesAfterDie(enemy);
                iterator.remove();
            }
            if(enemy instanceof Barricados){
                Barricados barricados = (Barricados) enemy;
                if(barricados.isExpired()){
                    enemy.getCurrentFrame().removeFromGamePanel(enemy);
                    enemy.getCurrentFrame().setVisible(false);
                    game.getGameFrames().remove(enemy.getCurrentFrame());
                    iterator.remove();
                }
            }
        }

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
        else if(waveController.getCurrentDelay() > (5 - currentWaveIndex) * 1000){
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
