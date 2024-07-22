package Config;

import java.util.ArrayList;

public class LevelConfig {
    ArrayList<BlackOrbConfig> blackOrbConfigs;
    ArrayList<EnemyConfig> enemyConfigs;
    EpsilonConfig epsilonConfig;

    public ArrayList<BlackOrbConfig> getBlackOrbConfigs() {
        return blackOrbConfigs;
    }

    public void setBlackOrbConfigs(ArrayList<BlackOrbConfig> blackOrbConfigs) {
        this.blackOrbConfigs = blackOrbConfigs;
    }

    public ArrayList<EnemyConfig> getEnemyConfigs() {
        return enemyConfigs;
    }

    public void setEnemyConfigs(ArrayList<EnemyConfig> enemyConfigs) {
        this.enemyConfigs = enemyConfigs;
    }

    public EpsilonConfig getEpsilonConfig() {
        return epsilonConfig;
    }

    public void setEpsilonConfig(EpsilonConfig epsilonConfig) {
        this.epsilonConfig = epsilonConfig;
    }
}
