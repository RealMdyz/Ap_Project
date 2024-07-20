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
    private Game game;
    public GameData(Game game) {
        this.game = game;
    }

    public static void saveGame(Game game) {
        GameData gameData = new GameData(
            game
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}