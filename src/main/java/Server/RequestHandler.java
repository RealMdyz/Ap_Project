package Server;

import Models.Epsilon.Epsilon;
import View.Menu.GameHistoryPage;
import View.Menu.RankingsPanel;
import View.Menu.SquadInfoPanel;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
        else if (response.startsWith("InitiateSquadBattle")) {
            initiateSquadBattle();
        }
        else if (response.startsWith("ViewSquadInfo")) {
            viewSquadInfo(response); // پردازش درخواست مشاهده اطلاعات Squad
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
    private void initiateSquadBattle() {
        loadSquads();
        ArrayList<Squad> activeSquads = new ArrayList<>(squads);

        Collections.shuffle(activeSquads);
        squads = activeSquads;
        for (int i = 0; i < activeSquads.size() - 1; i += 2) {
            Squad squad1 = activeSquads.get(i);
            Squad squad2 = activeSquads.get(i + 1);

            notifySquadMembers(squad1, "You are in a battle with " + squad2.getSquadName());
            notifySquadMembers(squad2, "You are in a battle with " + squad1.getSquadName());
        }
    }

    private void notifySquadMembers(Squad squad, String message) {
        for (String memberName : squad.getMembersName()) {
            JOptionPane.showMessageDialog(null, message, "Squad Battle Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void viewSquadInfo(String request) {
        loadSquads();
        String[] parts = request.split(" ");
        String playerName = parts[1];

        for (Squad squad : squads) {
            if (squad.getMembersName().contains(playerName)) {
                StringBuilder info = new StringBuilder();
                info.append("Squad Name: ").append(squad.getSquadName()).append("\n");
                info.append("Owner: ").append(squad.getOwnersName()).append("\n");
                info.append("Vault XP: ").append(squad.getVaultXP()).append("\n");
                info.append("Members:\n");

                for (String member : squad.getMembersName()) {
                    info.append(" - ").append(member).append("\n");
                }
                new SquadInfoPanel(info.toString());
                return;
            }
        }
    }
    private void handleActivateAbility(String request, PrintWriter out) {
        String[] parts = request.split(" ");
        String playerName = parts[1];
        String ability = parts[2];

        for (Squad squad : squads) {
            if (squad.getMembersName().contains(playerName) && squad.getOwnersName().equals(playerName)) {
                boolean success = false;

                switch (ability) {
                    case "CallOfPalioxis":
                        success = squad.activateCallOfPalioxis();
                        break;
                    case "CallOfAdonis":
                        success = squad.activateCallOfAdonis();
                        break;
                    case "CallOfGefjon":
                        success = squad.activateCallOfGefjon();
                        break;
                    default:
                        out.println("ActivateAbilityResponse Failed InvalidAbility");
                        return;
                }

                if (success) {
                    saveSquads();
                    out.println("ActivateAbilityResponse Success");
                } else {
                    out.println("ActivateAbilityResponse Failed InsufficientXP");
                }
                return;
            }
        }
        out.println("ActivateAbilityResponse Failed NotInSquad");
    }
    private void terminateSquadBattle(PrintWriter out) {
        loadSquads();
        ArrayList<Squad> activeSquads = new ArrayList<>(squads);

        if (activeSquads.size() < 2) {
            out.println("TerminateSquadBattle Response Failed NotEnoughSquads");
            return;
        }
        for (int i = 0; i < activeSquads.size() - 1; i += 2) {
            Squad squad1 = activeSquads.get(i);
            Squad squad2 = activeSquads.get(i + 1);

            int xp1 = squad1.getXpInBattle();
            int xp2 = squad2.getXpInBattle();

            Squad winner = null;

            if (xp1 > xp2) {
                winner = squad1;
            } else if (xp2 > xp1) {
                winner = squad2;
            } else {
                // تساوی در XP
                if (squad1.getMonomachiaWins() > squad2.getMonomachiaWins()) {
                    winner = squad1;
                } else if (squad2.getMonomachiaWins() > squad1.getMonomachiaWins()) {
                    winner = squad2;
                } else {
                    // تساوی در بردهای Monomachia
                    if (squad1.isCallOfGefjonActive() && !squad2.isCallOfGefjonActive()) {
                        winner = squad1;
                    } else if (!squad1.isCallOfGefjonActive() && squad2.isCallOfGefjonActive()) {
                        winner = squad2;
                    } else {
                        // تساوی در همه معیارها
                        winner = new Random().nextBoolean() ? squad1 : squad2;
                    }
                }
            }

            // اعلام برنده
            if (winner != null) {
                out.println("TerminateSquadBattle Response Success Winner: " + winner.getSquadName());
            } else {
                out.println("TerminateSquadBattle Response Failed NoWinner");
            }


        }

        SwingUtilities.invokeLater(() -> new RankingsPanel(activeSquads));
        // Reset XP and Monomachia wins
        for (Squad squad : activeSquads) {
            squad.setXpInBattle(0);
            squad.setMonomachiaWins(0);
        }
        saveSquads();

    }
}
