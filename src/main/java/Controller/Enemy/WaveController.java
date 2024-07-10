package Controller.Enemy;

import Models.Constant;

public class WaveController {

    private int[] enemiesToKillEachWave;
    private long currentDelay = 0;
    private long waveStartTime;
    private double spawnRateMultiplier = 1.0;

    public WaveController(){
        enemiesToKillEachWave = new int[5];
        for(int i = 0; i < 5; i++){
            enemiesToKillEachWave[i] = Constant.getLevel() / 20 * (3) + (i + 1) * 2;

            System.out.println(i + " " + enemiesToKillEachWave[i]);
        }
        waveStartTime = System.currentTimeMillis();
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

    public double getSpawnRateMultiplier() {
        long elapsedTime = System.currentTimeMillis() - waveStartTime;
        spawnRateMultiplier = 1.0 + (elapsedTime / 60000.0); // افزایش سختی به ازای هر دقیقه
        return spawnRateMultiplier;
    }
}
