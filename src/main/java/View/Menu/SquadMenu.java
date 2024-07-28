package View.Menu;

import Models.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class SquadMenu extends JFrame implements ActionListener {
    private PrintWriter out;
    private JTextField squadNameField;
    private JTextField playerNameField;
    private JButton createSquadButton;
    private JButton joinSquadButton;
    private JButton leaveSquadButton;
    private JTextArea squadInfoArea;

    public SquadMenu(PrintWriter out) {
        this.out = out;

        this.setSize(400, 400);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));

        squadNameField = new JTextField();
        playerNameField = new JTextField();
        createSquadButton = new JButton("Create Squad");
        joinSquadButton = new JButton("Join Squad");
        leaveSquadButton = new JButton("Leave Squad");

        createSquadButton.addActionListener(this);
        joinSquadButton.addActionListener(this);
        leaveSquadButton.addActionListener(this);

        inputPanel.add(new JLabel("Squad Name:"));
        inputPanel.add(squadNameField);
        inputPanel.add(new JLabel("Player Name:"));
        inputPanel.add(playerNameField);
        inputPanel.add(createSquadButton);
        inputPanel.add(joinSquadButton);
        inputPanel.add(leaveSquadButton);

        squadInfoArea = new JTextArea();
        squadInfoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(squadInfoArea);

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String squadName = squadNameField.getText().trim();
        String playerName = playerNameField.getText().trim();

        if (e.getSource() == createSquadButton) {
            if (!squadName.isEmpty() && !playerName.isEmpty() && Constant.getSavedXp() >= 100) {
                out.println("CreateSquad " + playerName + " " + squadName);
                System.out.println("CreateSquad " + playerName + " " + squadName);
                Constant.setSavedXp(Constant.getPlayerXP() - 100);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both squad name and player name.");
            }
        } else if (e.getSource() == joinSquadButton) {
            if (!squadName.isEmpty() && !playerName.isEmpty()) {
                out.println("JoinSquad " + playerName + " " + squadName);
                System.out.println("JoinSquad " + playerName + " " + squadName);

            } else {
                JOptionPane.showMessageDialog(this, "Please enter both squad name and player name.");
            }
        } else if (e.getSource() == leaveSquadButton) {
            if (!playerName.isEmpty()) {
                out.println("LeaveSquad " + playerName);
                System.out.println("LeaveSquad " + playerName);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your player name.");
            }
        }
    }
}