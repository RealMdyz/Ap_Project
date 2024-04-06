package Models.Enemy;

import Models.Constant;
import Models.ObjectsInGame;

import java.util.ArrayList;

public class EnemyWave {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int numEnemies;
    private int delay;
    private boolean waveOver;
    public EnemyWave(int numEnemies, int delay) {
        this.numEnemies = numEnemies;
        this.delay = delay;
        this.waveOver = false;
        for (int i = 0; i < numEnemies; i++) {
            // Add Enemy to enemies;
            if(i % 2 == 0){
                enemies.add(new Squarantine(numEnemies *  Constant.getWidthOfTrighrath(), Constant.getHeightOfTrighrath() * i + Constant.getHeightOfTrighrath()));
            }
            else {
                enemies.add(new Trigorath(numEnemies * Constant.getWidthOfSquarantine(),  Constant.getHeightOfSquarantine() * i + Constant.getHeightOfSquarantine()));
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getNumEnemies() {
        return numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isWaveOver() {
        return waveOver;
    }

    public void setWaveOver(boolean waveOver) {
        this.waveOver = waveOver;
    }
}
