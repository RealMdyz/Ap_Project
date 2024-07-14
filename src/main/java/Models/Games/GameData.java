package Models.Games;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Epsilon.Epsilon;
import Models.Game;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameData {
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<BlackOrb> blackOrbs;
    private Epsilon epsilon;
    private Constant constant;

    public GameData(ArrayList<Enemy> enemyArrayList, ArrayList<BlackOrb> blackOrbs, Epsilon epsilon, Constant constant) {
        this.enemyArrayList = enemyArrayList;
        this.blackOrbs = blackOrbs;
        this.epsilon = epsilon;
        this.constant = constant;
    }

    public static void saveGame(Game game) {
        GameData gameData = new GameData(
                game.getEnemyController().getEnemyArrayList(),
                game.getEnemyController().getBlackOrbs(),
                game.getEpsilon(),
                game.getConstant()

        );

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("gameData.json"), gameData);
            System.out.println("Game data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameData loadGame() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            GameData gameData = mapper.readValue(new File("gameData.json"), GameData.class);
            System.out.println("Loaded game data: " + gameData);
            return gameData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    public void setEnemyArrayList(ArrayList<Enemy> enemyArrayList) {
        this.enemyArrayList = enemyArrayList;
    }

    public ArrayList<BlackOrb> getBlackOrbs() {
        return blackOrbs;
    }

    public void setBlackOrbs(ArrayList<BlackOrb> blackOrbs) {
        this.blackOrbs = blackOrbs;
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Epsilon epsilon) {
        this.epsilon = epsilon;
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
    }
}