package View.Menu;


import Server.Squad;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankingsPanel extends JFrame {

    public RankingsPanel(ArrayList<Squad> squads) {
        setTitle("Squad Rankings");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sort squads based on XP, Monomachia wins, and Call of Gefjon
        Collections.sort(squads, new Comparator<Squad>() {
            @Override
            public int compare(Squad s1, Squad s2) {
                if (s1.getXpInBattle() != s2.getXpInBattle()) {
                    return Integer.compare(s2.getXpInBattle(), s1.getXpInBattle());
                } else if (s1.getMonomachiaWins() != s2.getMonomachiaWins()) {
                    return Integer.compare(s2.getMonomachiaWins(), s1.getMonomachiaWins());
                } else if (s1.isCallOfGefjonActive() != s2.isCallOfGefjonActive()) {
                    return Boolean.compare(s2.isCallOfGefjonActive(), s1.isCallOfGefjonActive());
                } else {
                    return 0;
                }
            }
        });

        StringBuilder rankings = new StringBuilder();
        rankings.append("Rankings:\n\n");
        int rank = 1;
        for (Squad squad : squads) {
            rankings.append("Rank ").append(rank++).append(": ").append(squad.getSquadName()).append("\n");
            rankings.append("  XP: ").append(squad.getXpInBattle()).append("\n");
            rankings.append("  Monomachia Wins: ").append(squad.getMonomachiaWins()).append("\n");
            rankings.append("  Call of Gefjon Active: ").append(squad.isCallOfGefjonActive()).append("\n\n");
        }

        JTextArea rankingsArea = new JTextArea(rankings.toString());
        rankingsArea.setEditable(false);
        add(new JScrollPane(rankingsArea), BorderLayout.CENTER);

        setVisible(true);
    }
}

