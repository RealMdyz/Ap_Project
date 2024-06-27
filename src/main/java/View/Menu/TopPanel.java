package View.Menu;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    // Labels for displaying Wave, Elapsed Time, XP, and HP
    private JLabel waveLabel;
    private JLabel timeLabel;
    private JLabel xpLabel;
    private JLabel hpLabel;

    // Labels for displaying abilities and Epsilon capability
    private JLabel abilityAttack;
    private JLabel abilityDefend;
    private JLabel abilityChangeShape;

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

        // Initialize labels for abilities and Epsilon capability
        abilityAttack = new JLabel();
        abilityDefend = new JLabel();
        abilityChangeShape = new JLabel();

        // Add ability and Epsilon labels to the panel
        this.add(abilityAttack);
        this.add(abilityDefend);
        this.add(abilityChangeShape);


        // Optional: Customize appearance of labels if needed
        customizeLabels();
    }

    private void customizeLabels() {
        // Example: Set font and foreground color for labels
        Font labelFont = new Font("Arial", Font.BOLD, 16);
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
    public void updateWaveLabel(int wave, int endWave) {
        if (wave == 3)
            wave = 2;
        waveLabel.setText("Wave: " + (wave + 1) + "/" + endWave);
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

    public void updateAbilityAttackIcon(ImageIcon icon) {
        abilityAttack.setIcon(resizeIcon(icon, 50, 50));
        abilityAttack.setVisible(true);
    }

    public void updateAbilityDefendIcon(ImageIcon icon) {
        abilityDefend.setIcon(resizeIcon(icon, 50, 50));
        abilityDefend.setVisible(true);
    }

    public void updateAbilityChangeShapeIcon(ImageIcon icon) {
        abilityChangeShape.setIcon(resizeIcon(icon, 50, 50));
        abilityChangeShape.setVisible(true);
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}