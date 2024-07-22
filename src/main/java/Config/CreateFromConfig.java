package Config;

import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.Normal.Archmire;
import Models.Enemy.Normal.Necropick;
import Models.Enemy.Normal.Omenoct;
import Models.Enemy.Normal.Wyrm;
import Models.Epsilon.Epsilon;
import Models.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFromConfig {

    private ObjectMapper objectMapper;

    public CreateFromConfig() {
        objectMapper = new ObjectMapper();
    }

    public void createLevelConfig(Game game) {
        LevelConfig levelConfig = new LevelConfig();

        EpsilonConfig epsilonConfig = new EpsilonConfig();
        epsilonConfig.x = game.getEpsilon().getX();
        epsilonConfig.y = game.getEpsilon().getY();

        ArrayList<EnemyConfig> enemyConfigs = new ArrayList<>();
        for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
            enemyConfigs.add(getEnemyConfigFromEnemy(enemy));
        }

        ArrayList<BlackOrbConfig> blackOrbConfigs = new ArrayList<>();
        for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
            blackOrbConfigs.add(getBlackOrbConfigFromBlackOrb(blackOrb));
        }

        levelConfig.setEnemyConfigs(enemyConfigs);
        levelConfig.setEpsilonConfig(epsilonConfig);
        levelConfig.setBlackOrbConfigs(blackOrbConfigs);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("gameData.json"), levelConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LevelConfig loadLevelConfig() {
        LevelConfig levelConfig = null;
        try {
            levelConfig = objectMapper.readValue(new File("gameData.json"), LevelConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelConfig;
    }
    public EnemyConfig getEnemyConfigFromEnemy(Enemy enemy){
        EnemyConfig enemyConfig = new EnemyConfig();
        enemyConfig.x = enemy.getX();
        enemyConfig.y = enemy.getY();
        if(enemy instanceof Omenoct)
            enemyConfig.type = "Omenoct";
        else if(enemy instanceof Necropick){
            enemyConfig.type = "Necropick";
        }
        else if(enemy instanceof Archmire){
            enemyConfig.type = "Archmire";
        }
        else if(enemy instanceof Wyrm){
            enemyConfig.type = "Wyrm";
        }
        else{
            enemyConfig.type = "Barricados";
        }
        return enemyConfig;
    }
    public BlackOrbConfig getBlackOrbConfigFromBlackOrb(BlackOrb blackOrb){
        BlackOrbConfig blackOrbConfig = new BlackOrbConfig();
        blackOrbConfig.xCenter = blackOrb.getxCenter();
        blackOrbConfig.yCenter = blackOrb.getyCenter();
        return blackOrbConfig;
    }

}
