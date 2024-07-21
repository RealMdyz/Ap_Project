package View.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JFrame {
    private int totalShots;
    private int successfulShots;
    private int enemiesKilled;
    private int xpGained;
    private long duration;

    public GameOverPanel(int totalShots, int successfulShots, int enemiesKilled, int xpGained, long duration) {
        this.totalShots = totalShots;
        this.successfulShots = successfulShots;
        this.enemiesKilled = enemiesKilled;
        this.xpGained = xpGained;
        this.duration = duration;

        setTitle("Game Over");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Game Over", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        panel.add(new JLabel("Total Shots: " + totalShots, SwingConstants.CENTER));
        panel.add(new JLabel("Successful Shots: " + successfulShots, SwingConstants.CENTER));
        panel.add(new JLabel("Enemies Killed: " + enemiesKilled, SwingConstants.CENTER));
        panel.add(new JLabel("XP Gained: " + xpGained, SwingConstants.CENTER));
        panel.add(new JLabel("Duration: " + (duration / 1000) + " seconds", SwingConstants.CENTER));

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to go back to menu
                System.out.println("Returning to menu...");
                dispose(); // Close the game over window
            }
        });
        panel.add(backButton);

        add(panel);
    }

}

