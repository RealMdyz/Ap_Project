package View.Menu;

import Controller.Game.GameLoop;
import Models.Constant;
import Models.Game;
import MyProject.MyProjectData;
import Server.History;
import Server.LeaderBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class LoginPageShare extends JFrame implements ActionListener {
    JButton startButton;
    JButton settingButton;
    JButton tutorialButton;
    JButton skillTreeButton;
    JButton viewLeaderboardButton;

    JButton exitButton;
    JButton connectServerButton; // Button for connecting to server
    JButton offlinePlayButton; // Button for offline play
    JLayeredPane backgroundPanel;
    JLabel backgroundImageLabel;

    protected Constant constant;
    public  Socket socket;


    public LoginPageShare(Constant constant) {
        this.constant = constant;

        ImageIcon backgroundImage = MyProjectData.getProjectData().getGameMenuImage();
        ImageIcon gameIcon = MyProjectData.getProjectData().getGameIcon();
        Font font20 = MyProjectData.getProjectData().getFont20();
        Font font10 = MyProjectData.getProjectData().getFont10();
        this.setSize(650, 700);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setIconImage(gameIcon.getImage());

        backgroundPanel = new JLayeredPane();
        backgroundPanel.setBounds(0, 0, 700, 700);

        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 700, 700);
        backgroundImageLabel.setOpaque(true);

        // Initialize buttons

        startButton = createButton("Start", 100, 100, font20);
        settingButton = createButton("Settings", 80, 200, font20);
        tutorialButton = createButton("Tutorial", 400, 500, font20);
        skillTreeButton = createButton("Skill Tree", 250, 350, font20);
        exitButton = createButton("Exit", 0, 0, font10);
        connectServerButton = createButton("Connect to Server", 100, 300, font20);
        offlinePlayButton = createButton("Play Offline", 100, 400, font20);
        viewLeaderboardButton = createButton("View Leaderboard", 100, 500, MyProjectData.getProjectData().getFont20()); // دکمه جدید

        // Add action listeners
        startButton.addActionListener(this);
        settingButton.addActionListener(this);
        tutorialButton.addActionListener(this);
        skillTreeButton.addActionListener(this);
        exitButton.addActionListener(this);
        connectServerButton.addActionListener(this);
        offlinePlayButton.addActionListener(this);
        viewLeaderboardButton.addActionListener(this);

        // Add components to the background panel
        backgroundPanel.add(backgroundImageLabel, Integer.valueOf(0));
        backgroundPanel.add(startButton, Integer.valueOf(1));
        backgroundPanel.add(settingButton, Integer.valueOf(1));
        backgroundPanel.add(skillTreeButton, Integer.valueOf(1));
        backgroundPanel.add(tutorialButton, Integer.valueOf(1));
        backgroundPanel.add(exitButton, Integer.valueOf(1));
        backgroundPanel.add(connectServerButton, Integer.valueOf(1));
        backgroundPanel.add(offlinePlayButton, Integer.valueOf(1));
        backgroundPanel.add(viewLeaderboardButton, Integer.valueOf(1));

        this.add(backgroundPanel);

        Constant.clientName  = JOptionPane.showInputDialog(this, "Enter client name:", "Register", JOptionPane.PLAIN_MESSAGE);
        repaint();
    }

    private JButton createButton(String text, int x, int y, Font font) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 50);
        button.setBackground(Color.RED);
        button.setForeground(Color.BLACK);
        button.setFocusable(false);
        button.setFont(font);
        return button;
    }
    private void startGame() {
        constant = new Constant();
        Game game = new Game(constant);
        GameLoop gameLoop = new GameLoop(game, constant);
        game.getEpsilonFrame().setVisible(true);
        repaint();
    }
    private void openSettings() {
        SettingPage settingPage = new SettingPage(constant);
        repaint();
    }

    private void openTutorial() {
        // Implement tutorial logic
    }
    private void openSkillTree() {
        SkillTreePanel skillTreePanel = new SkillTreePanel(this, constant);
        skillTreePanel.setPreferredSize(new Dimension(650, 700));
        skillTreePanel.setVisible(true);

        backgroundPanel.add(skillTreePanel, Integer.valueOf(2));
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
    }
    private void connectToServer() {
        String serverAddress = JOptionPane.showInputDialog(this, "Enter server address:", "Connect to Server", JOptionPane.PLAIN_MESSAGE);
        if (serverAddress != null && !serverAddress.isEmpty()) {
            try {
                socket = new Socket(serverAddress, 12345);
                JOptionPane.showMessageDialog(this, "Connected to the server!");
                updateAllResult(socket);
                Constant.isOnline = true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to connect to the server.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateAllResult(Socket socket){
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard = leaderBoard.load("offlineResult.json");
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(History history : leaderBoard.getHistories()){
            String massage = "UpdBoard " + history.getName() + " " + history.getMostAliveTime() + " " + history.getMostGainedXp();
            out.println(massage);
        }
        out.println("END");
    }
    private void playOffline() {
        Constant.isOnline = false;
        JOptionPane.showMessageDialog(this, "Starting offline mode...");
        startGame(); // Start the game in offline mode
    }
    private void viewGameHistory() {
        if (Constant.isOnline && socket != null) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("NeedGameHistory");

                StringBuilder historyData = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("END")) break; // پایان داده‌ها
                    historyData.append(line).append("\n");
                }

                new GameHistoryPage(historyData.toString());
            } catch (IOException e) {
                e.printStackTrace(); // چاپ خطا برای بررسی بیشتر
                JOptionPane.showMessageDialog(this, "Error retrieving game history.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You are not connected to the server.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startGame();
        } else if (e.getSource() == settingButton) {
            openSettings();
        } else if (e.getSource() == tutorialButton) {
            this.setVisible(false);
            openTutorial();
        } else if (e.getSource() == skillTreeButton) {
            openSkillTree();
        } else if (e.getSource() == exitButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == connectServerButton) {
            connectToServer();
        } else if (e.getSource() == offlinePlayButton) {
            playOffline();
        }
        else if (e.getSource() == viewLeaderboardButton) {
            viewGameHistory();
        }
    }
}
