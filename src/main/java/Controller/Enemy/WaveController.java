package Controller.Enemy;

import Models.Constant;

public class WaveController {

    private int[] numberOfEnemyInEachWave;
    private int[] startOfWave;
    private long currentDelay = 0;

    public WaveController(){
        numberOfEnemyInEachWave = new int[5];
        startOfWave = new int[5];
        for(int i = 0; i < 5; i++){
            numberOfEnemyInEachWave[i] = Constant.getLevel() / 20 * (3) + (i + 1);
        }
    }

    public int[] getNumberOfEnemyInEachWave() {
        return numberOfEnemyInEachWave;
    }

    public void setNumberOfEnemyInEachWave(int[] numberOfEnemyInEachWave) {
        this.numberOfEnemyInEachWave = numberOfEnemyInEachWave;
    }

    public int[] getStartOfWave() {
        return startOfWave;
    }

    public void setStartOfWave(int[] startOfWave) {
        this.startOfWave = startOfWave;
    }

    public long getCurrentDelay() {
        return currentDelay;
    }

    public void setCurrentDelay(int currentDelay) {
        this.currentDelay = currentDelay;
    }
}
