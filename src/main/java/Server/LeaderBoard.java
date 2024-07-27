package Server;

import Config.LevelConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;

public class LeaderBoard {
    private ArrayList<History> histories;
    private ObjectMapper objectMapper;

    public LeaderBoard() {
        histories = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }

    public void save(String filePath) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LeaderBoard load(String filePath) {
        LeaderBoard leaderBoard = null;
        try {
            leaderBoard = objectMapper.readValue(new File(filePath), LeaderBoard.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderBoard;
    }

    // Getter for histories
    public ArrayList<History> getHistories() {
        return histories;
    }
}
