package View.Menu;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopPanel extends JPanel {
    // Labels for displaying Wave, Elapsed Time, XP, and HP
    private JLabel waveLabel;
    private JLabel timeLabel;
    private JLabel xpLabel;
    private JLabel hpLabel;

    public TopPanel() {
        this.setVisible(true);
        this.setOpaque(false);
        // Set layout for the top panel
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Adjust spacing as needed

        // Create and add labels to display Wave, Elapsed Time, XP, and HP
        waveLabel = new JLabel("Wave: ");
        this.add(waveLabel);

        timeLabel = new JLabel("Time: ");
        this.add(timeLabel);

        xpLabel = new JLabel("XP: ");
        this.add(xpLabel);

        hpLabel = new JLabel("HP: ");
        this.add(hpLabel);

        // Optional: Customize appearance of labels if needed
        customizeLabels();
    }

    private void customizeLabels() {
        // Example: Set font and foreground color for labels
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Color labelColor = Color.WHITE;

        waveLabel.setFont(labelFont);
        waveLabel.setForeground(labelColor);

        timeLabel.setFont(labelFont);
        timeLabel.setForeground(labelColor);

        xpLabel.setFont(labelFont);
        xpLabel.setForeground(labelColor);

        hpLabel.setFont(labelFont);
        hpLabel.setForeground(labelColor);
    }

    // Methods to update label texts
    public void updateWaveLabel(int wave) {
        waveLabel.setText("Wave: " + (wave + 1) + "/3");
    }

    public void updateTimeLabel(long timeInMillis) {
        long hours = (timeInMillis / (1000 * 60 * 60)) % 24;
        long minutes = (timeInMillis / (1000 * 60)) % 60;
        long seconds = (timeInMillis / 1000) % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText("Time: " + formattedTime);
    }


    public void updateXPLabel(int xp) {
        xpLabel.setText("XP: " + xp);
    }

    public void updateHPLabel(int hp) {
        hpLabel.setText("HP: " + hp);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
