package Controller.Enemy;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.Barricados.Barricados;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.Normal.Necropick;
import Models.Game;

import java.util.ArrayList;
import java.util.Iterator;
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
        if(currentWaveIndex > 3)
            blackOrbSpawnProcess();
        for(Enemy enemy : enemyArrayList){
            enemy.specialPowers(game.getEpsilon());
            enemy.move();
        }
        removingEnemies();
    }
    public void blackOrbSpawnProcess(){
        if(lastBlackOrbDone && Math.random() < 0.005 && blackOrbs.size() == 0){
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
            if(blackOrb.getSpawnChuck() == 5){
                lastBlackOrbDone = true;
                blackOrb.reDrawLasers();
            }

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
                currentWaveEnemyDied += 1;
            }
            if(enemy instanceof Barricados){
                Barricados barricados = (Barricados) enemy;
                if(barricados.isExpired()){
                    enemy.getCurrentFrame().removeFromGamePanel(enemy);
                    enemy.getCurrentFrame().setVisible(false);
                    game.getGameFrames().remove(enemy.getCurrentFrame());
                    iterator.remove();
                    currentWaveEnemyDied += 1;
                }
            }
        }

    }
    public void spawnProcess(){
        if(currentWaveIndex >= waveController.getEnemiesToKillEachWave().length){
            return;
        }
        if(currentWaveEnemyDied >= waveController.getEnemiesToKillEachWave()[currentWaveIndex]){
            if(enemyArrayList.isEmpty()){
                currentWaveIndex += 1;
                currentWaveEnemyDied = 0;
                waveController.resetWaveStartTime();
                game.getCheckPointController().control(currentWaveIndex, game.getEpsilon());
                waveController.getEndOfEachWave()[currentWaveIndex - 1] = System.currentTimeMillis();
                waveController.getStartOfEachWave()[currentWaveIndex] = System.currentTimeMillis();
            }
        } else if(waveController.getCurrentDelay() >  Constant.SPAWN_PROCESS_RATE / waveController.getSpawnRateMultiplier()){
            waveController.setCurrentDelay(0);
            Random random = new Random();
            addEnemy(makeEnemy.makeRandomEnemy(random.nextInt(), currentWaveIndex));
        }


        waveController.setCurrentDelay(waveController.getCurrentDelay() + (long) (2L * currentWaveIndex + 5));
    }

    public ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    public void setEnemyArrayList(ArrayList<Enemy> enemyArrayList) {
        this.enemyArrayList = enemyArrayList;
    }

    public int getCurrentWaveIndex() {
        return currentWaveIndex;
    }

    public void setCurrentWaveIndex(int currentWaveIndex) {
        this.currentWaveIndex = currentWaveIndex;
    }

    public ArrayList<BlackOrb> getBlackOrbs() {
        return blackOrbs;
    }

    public void setBlackOrbs(ArrayList<BlackOrb> blackOrbs) {
        this.blackOrbs = blackOrbs;
    }

    public WaveController getWaveController() {
        return waveController;
    }

    public void setWaveController(WaveController waveController) {
        this.waveController = waveController;
    }
}
