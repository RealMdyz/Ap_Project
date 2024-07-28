package Server;

import Models.Epsilon.Epsilon;
import View.Menu.GameHistoryPage;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RequestHandler {
    private ArrayList<Squad> squads = new ArrayList<>();
    private static final String SQUADS_FILE = "squads.json";
    private ObjectMapper objectMapper = new ObjectMapper();


    public void handel(String response,  PrintWriter out){
        if(response.startsWith("UpdBoard")){
            String[] parts = response.split(" ");
            LeaderBoard leaderBoard = new LeaderBoard();
            leaderBoard = leaderBoard.load("allResult.json");
            boolean c = false;
            for(History history : leaderBoard.getHistories()){
                if(history.getName().equals(parts[1])){
                    long aliveTime = Integer.parseInt(parts[2]);
                    int xp = Integer.parseInt(parts[3]);
                    if(history.getMostAliveTime() < aliveTime)
                        history.setMostAliveTime(aliveTime);
                    if(history.getMostAliveTime() < xp)
                        history.setMostGainedXp(xp);
                    c = true;
                }
            }
            if(c == false){
                History history = new History();
                long aliveTime = Integer.parseInt(parts[2]);
                int xp = Integer.parseInt(parts[3]);
                history.setName(parts[1]);
                history.setMostGainedXp(xp);
                history.setMostAliveTime(aliveTime);
                leaderBoard.getHistories().add(history);
            }
            leaderBoard.save("allResult.json");
        }
        else if (response.startsWith("NeedGameHistory")) {
            showGameHistory();
        }
        else if (response.startsWith("RequestSquadInfo")) {
            sendSquadInfo(out);
        } else if (response.startsWith("JoinSquad")) {
            handleJoinSquad(response, out);
        } else if (response.startsWith("CreateSquad")) {
            handleCreateSquad(response, out);
        } else if (response.startsWith("LeaveSquad")) {
            handleLeaveSquad(response, out);
        }

    }

    private void loadSquads() {
        try {
            File file = new File(SQUADS_FILE);
            if (file.exists()) {
                squads = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Squad.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSquads() {
        try {
            objectMapper.writeValue(new File(SQUADS_FILE), squads);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showGameHistory() {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard = leaderBoard.load("allResult.json");

        StringBuilder historyData = new StringBuilder();
        for (History history : leaderBoard.getHistories()) {
            historyData.append("Name: ").append(history.getName()).append("\n");
            historyData.append("Most Alive Time: ").append(history.getMostAliveTime()).append("\n");
            historyData.append("Most Gained XP: ").append(history.getMostGainedXp()).append("\n\n");
        }

        SwingUtilities.invokeLater(() -> new GameHistoryPage(historyData.toString()));
    }

    private void sendSquadInfo(PrintWriter out) {
        for (Squad squad : squads) {
            out.println("SquadInfo " + squad.getSquadName() + " " + squad.getMembersName().size());
        }
        out.println("END");
    }

    private void handleJoinSquad(String response, PrintWriter out) {
        String[] parts = response.split(" ");
        String playerName = parts[1];
        String squadName = parts[2];

        for (Squad squad : squads) {
            if (squad.getSquadName().equals(squadName)) {
                if (squad.getMembersName().contains(playerName)) {
                    out.println("JoinSquadResponse Failed AlreadyInSquad");
                    return;
                }
                squad.getMembersName().add(playerName);
                saveSquads();
                out.println("JoinSquadResponse Success");
                return;
            }
        }
        out.println("JoinSquadResponse Failed SquadNotFound");
    }

    private void handleCreateSquad(String response, PrintWriter out) {
        String[] parts = response.split(" ");
        String playerName = parts[1];
        String squadName = parts[2];

        for (Squad squad : squads) {
            if (squad.getSquadName().equals(squadName)) {
                out.println("CreateSquadResponse Failed SquadAlreadyExists");
                return;
            }
            if (squad.getOwnersName().equals(playerName)) {
                out.println("CreateSquadResponse Failed AlreadyOwnsSquad");
                return;
            }
        }

        Squad squad = new Squad();
        squad.setSquadName(squadName);
        squad.setOwnersName(playerName);
        squad.getMembersName().add(playerName);
        squads.add(squad);
        saveSquads();
        out.println("CreateSquadResponse Success");
    }

    private void handleLeaveSquad(String response, PrintWriter out) {
        String[] parts = response.split(" ");
        String playerName = parts[1];

        for (Squad squad : squads) {
            if (squad.getMembersName().contains(playerName)) {
                if (squad.getOwnersName().equals(playerName)) {
                    out.println("LeaveSquadResponse Failed OwnerCannotLeave");
                    return;
                }
                squad.getMembersName().remove(playerName);
                saveSquads();
                out.println("LeaveSquadResponse Success");
                return;
            }
        }
        out.println("LeaveSquadResponse Failed NotInSquad");
    }
}
