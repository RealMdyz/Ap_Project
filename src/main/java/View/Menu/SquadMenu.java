package View.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class SquadMenu extends JFrame implements ActionListener {
    private JTextField playerNameField;
    private JTextField squadNameField;
    private JButton createSquadButton;
    private JButton joinSquadButton;
    private JButton leaveSquadButton;
    private JButton initiateBattleButton;
    private JButton viewSquadInfoButton;
    private JTextArea squadInfoArea;

    private JButton activateCallOfPalioxisButton;
    private JButton activateCallOfAdonisButton;
    private JButton activateCallOfGefjonButton;
    private PrintWriter out;
    public SquadMenu(PrintWriter out) {
        this.out = out;

        setTitle("Squad Page");
        setSize(400, 400); // تغییر اندازه برای افزودن دکمه‌های جدید
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2)); // افزایش تعداد ردیف‌ها برای دکمه‌های جدید

        panel.add(new JLabel("Player Name:"));
        playerNameField = new JTextField();
        panel.add(playerNameField);

        panel.add(new JLabel("Squad Name:"));
        squadNameField = new JTextField();
        panel.add(squadNameField);

        createSquadButton = new JButton("Create Squad");
        joinSquadButton = new JButton("Join Squad");
        leaveSquadButton = new JButton("Leave Squad");
        initiateBattleButton = new JButton("Initiate Squad Battle");
        viewSquadInfoButton = new JButton("View Squad Info");

        activateCallOfPalioxisButton = new JButton("Activate Call of Palioxis");
        activateCallOfAdonisButton = new JButton("Activate Call of Adonis");
        activateCallOfGefjonButton = new JButton("Activate Call of Gefjon");

        createSquadButton.addActionListener(this);
        joinSquadButton.addActionListener(this);
        leaveSquadButton.addActionListener(this);
        initiateBattleButton.addActionListener(this);
        viewSquadInfoButton.addActionListener(this);

        activateCallOfPalioxisButton.addActionListener(this);
        activateCallOfAdonisButton.addActionListener(this);
        activateCallOfGefjonButton.addActionListener(this);

        panel.add(createSquadButton);
        panel.add(joinSquadButton);
        panel.add(leaveSquadButton);
        panel.add(initiateBattleButton);
        panel.add(viewSquadInfoButton);

        panel.add(activateCallOfPalioxisButton);
        panel.add(activateCallOfAdonisButton);
        panel.add(activateCallOfGefjonButton);

        squadInfoArea = new JTextArea();
        squadInfoArea.setEditable(false);
        panel.add(new JScrollPane(squadInfoArea));

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createSquadButton) {
            createSquad();
        } else if (e.getSource() == joinSquadButton) {
            joinSquad();
        } else if (e.getSource() == leaveSquadButton) {
            leaveSquad();
        } else if (e.getSource() == initiateBattleButton) {
            if (promptForPassword()) {
                initiateSquadBattle();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect password. Battle initiation aborted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewSquadInfoButton) {
            viewSquadInfo();
        } else if (e.getSource() == activateCallOfPalioxisButton) {
            activateAbility("CallOfPalioxis");
        } else if (e.getSource() == activateCallOfAdonisButton) {
            activateAbility("CallOfAdonis");
        } else if (e.getSource() == activateCallOfGefjonButton) {
            activateAbility("CallOfGefjon");
        }
    }

    private void activateAbility(String ability) {
        String playerName = playerNameField.getText().trim();
        if (!playerName.isEmpty()) {
            out.println("ActivateAbility " + playerName + " " + ability);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the player name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createSquad() {
        String playerName = playerNameField.getText().trim();
        String squadName = squadNameField.getText().trim();

        if (!playerName.isEmpty() && !squadName.isEmpty()) {
            out.println("CreateSquad " + playerName + " " + squadName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both player and squad names.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void joinSquad() {
        String playerName = playerNameField.getText().trim();
        String squadName = squadNameField.getText().trim();

        if (!playerName.isEmpty() && !squadName.isEmpty()) {
            out.println("JoinSquad " + playerName + " " + squadName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both player and squad names.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leaveSquad() {
        String playerName = playerNameField.getText().trim();

        if (!playerName.isEmpty()) {
            out.println("LeaveSquad " + playerName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the player name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initiateSquadBattle() {
        out.println("InitiateSquadBattle");
    }

    private void viewSquadInfo() {
        String playerName = playerNameField.getText().trim();
        if (!playerName.isEmpty()) {
            out.println("ViewSquadInfo " + playerName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the player name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean promptForPassword() {
        final String PREDEFINED_PASSWORD = "ADMIN";

        // نمایش پنجره برای وارد کردن رمز عبور
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(
                this,
                new Object[] { "Enter password:", passwordField },
                "Password Required",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // بررسی اینکه کاربر دکمه OK را زده است
        if (option == JOptionPane.OK_OPTION) {
            // بررسی اینکه رمز عبور صحیح است
            String inputPassword = new String(passwordField.getPassword());
            return PREDEFINED_PASSWORD.equals(inputPassword);
        }
        return false;
    }
}
