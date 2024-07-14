package Controller.Enemy;

import Models.Constant;

public class WaveController {

    private int[] enemiesToKillEachWave;
    private long[] startOfEachWave;
    private long[] endOfEachWave;
    private long currentDelay = 0;
    private long waveStartTime;
    private double spawnRateMultiplier = 1.0;
    public WaveController(){
        enemiesToKillEachWave = new int[5];
        startOfEachWave = new long[5];
        endOfEachWave = new long[5];
        for(int i = 0; i < 5; i++){
            enemiesToKillEachWave[i] = Constant.getLevel() / 20 * (i) + 1;

            System.out.println(i + " " + enemiesToKillEachWave[i]);
        }
        getStartOfEachWave()[0] = System.currentTimeMillis();
        waveStartTime  = System.currentTimeMillis();
    }


    public int[] getEnemiesToKillEachWave() {
        return enemiesToKillEachWave;
    }

    public long getCurrentDelay() {
        return currentDelay;
    }

    public void setCurrentDelay(long currentDelay) {
        this.currentDelay = currentDelay;
    }

    public void resetWaveStartTime() {
        waveStartTime = System.currentTimeMillis();
    }

    public long[] getStartOfEachWave() {
        return startOfEachWave;
    }

    public void setStartOfEachWave(long[] startOfEachWave) {
        this.startOfEachWave = startOfEachWave;
    }

    public long[] getEndOfEachWave() {
        return endOfEachWave;
    }

    public void setEndOfEachWave(long[] endOfEachWave) {
        this.endOfEachWave = endOfEachWave;
    }

    public double getSpawnRateMultiplier() {
        long elapsedTime = System.currentTimeMillis() - waveStartTime;
        spawnRateMultiplier = 1.0 + (elapsedTime / 60000.0); // افزایش سختی به ازای هر دقیقه
        return spawnRateMultiplier;
    }
}
