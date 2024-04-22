package Models.Enemy;

import Models.Constant;
import Models.ObjectsInGame;

import java.util.ArrayList;
import java.util.Random;

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
            Random random = new Random();
            int xRandom = random.nextInt() % 400 + 350;
            int yRandom = random.nextInt() % 400 + 350;
            int x1 = random.nextInt();
            if(x1 % 2 == 1){
                x1 = 1;

            }
            else
                x1 = -1;
            int y1 = random.nextInt();
            if(y1 % 2 == 1){
                y1 = 1;
            }
            else
                y1 = -1;
            xRandom *= x1;
            yRandom *= y1;
            if(i % 2 == 0){
                enemies.add(new Squarantine(Constant.getWidthOfTrighrath() * (random.nextInt() % 4 + 1) + xRandom, Constant.getHeightOfTrighrath() * i + Constant.getHeightOfTrighrath() + yRandom));
            }
            else {
                enemies.add(new Trigorath(Constant.getWidthOfTrighrath() * (random.nextInt() % 4 + 1) + xRandom, Constant.getHeightOfTrighrath() * i + Constant.getHeightOfTrighrath() + yRandom));
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
