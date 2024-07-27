package Controller.Enemy;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.Normal.Barricados;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Game;
import Models.Games.MakeEnemy;

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
    public static int enemiesKilled = 0;

    public EnemyController(Game game){
        this.game = game;
        waveController = new WaveController();
        makeEnemy = new MakeEnemy(game);
    }
    public void controllingTheEnemies(){
        spawnProcess();
        if(currentWaveIndex == 4){
            blackOrbSpawnProcess();
            for(BlackOrb blackOrb : blackOrbs){
                for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks())
                    blackOrbChuck.specialPowers(game.getEpsilon());
            }
        }
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
                enemiesKilled += 1;
            }
            if(enemy instanceof Barricados){
                Barricados barricados = (Barricados) enemy;
                if(barricados.isExpired()){
                    barricados.getCurrentFrame().removeFromGamePanel(enemy);
                    barricados.getCurrentFrame().setVisible(false);
                    game.getGameFrames().remove(barricados.getCurrentFrame());
                    iterator.remove();
                    currentWaveEnemyDied += 1;
                    enemiesKilled += 1;

                }
            }
        }
        if(lastBlackOrbDone){
            BlackOrb blackOrb1 = new BlackOrb(0, 0);
            for(BlackOrb blackOrb : blackOrbs){
                for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks()){
                    if(blackOrbChuck.getHp() <= 0){
                        blackOrbChuck.getCurrentFrame().removeFromGamePanel(blackOrbChuck);
                        if(!blackOrbChuck.getCurrentFrame().equals(game.getEpsilon().getCurrentFrame()))
                            blackOrbChuck.getCurrentFrame().setVisible(false);
                        game.getGameFrames().remove(blackOrbChuck.getCurrentFrame());
                        game.getCollectibleController().addingCollectiblesAfterDie(blackOrbChuck);
                        blackOrb.getBlackOrbChucks().remove(blackOrbChuck);
                        currentWaveEnemyDied += 1;
                        enemiesKilled += 1;

                        break;
                    }
                }
                if(blackOrb.getBlackOrbChucks().size() == 0)
                    blackOrb1 = blackOrb;
            }
            blackOrbs.remove(blackOrb1);
        }
    }
    public void spawnProcess(){
        if(currentWaveIndex >= waveController.getEnemiesToKillEachWave().length){
            return;
        }
        if(currentWaveEnemyDied >= waveController.getEnemiesToKillEachWave()[currentWaveIndex]){
            if(enemyArrayList.isEmpty() && blackOrbs.isEmpty()){
                currentWaveIndex += 1;
                currentWaveEnemyDied = 0;
                waveController.resetWaveStartTime();
                if (currentWaveIndex >= 5)
                    return;
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

    public boolean isLastBlackOrbDone() {
        return lastBlackOrbDone;
    }

    public void setLastBlackOrbDone(boolean lastBlackOrbDone) {
        this.lastBlackOrbDone = lastBlackOrbDone;
    }

    public int getCurrentWaveEnemyDied() {
        return currentWaveEnemyDied;
    }

    public void setCurrentWaveEnemyDied(int currentWaveEnemyDied) {
        this.currentWaveEnemyDied = currentWaveEnemyDied;
    }

    public MakeEnemy getMakeEnemy() {
        return makeEnemy;
    }

    public void setMakeEnemy(MakeEnemy makeEnemy) {
        this.makeEnemy = makeEnemy;
    }
}
