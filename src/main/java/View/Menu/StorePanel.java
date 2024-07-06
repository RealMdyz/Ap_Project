package View.Menu;

import Models.Constant;
import Models.Epsilon.Epsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StorePanel extends JFrame implements ActionListener {
    Constant constant;
    JLabel xpLabel;
    MessagePanel messagePanel;
    Epsilon epsilon;

    public boolean newImpact = false;
    public int newImpactX, newImpactY;
    public StorePanel(Constant constant, Epsilon epsilon){
        this.constant = constant;
        this.epsilon = epsilon;
        this.setSize(650, 700);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setBackground(Color.lightGray);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(0, 0, 255);
                Color color2 = new Color(0, 255, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        gradientPanel.setLayout(null);
        gradientPanel.setBounds(0, 0, 650, 700);
        add(gradientPanel);

        // Label to display XP
        xpLabel = new JLabel("Your XP: " + Constant.getPlayerXP());
        xpLabel.setBounds(20, 20, 200, 30);
        xpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gradientPanel.add(xpLabel);


        // Buttons for purchasing abilities
        JButton oHephaestusButton = new JButton("Buy O' Hephaestus (100 XP)");
        oHephaestusButton.setBounds(20, 60, 300, 50);
        oHephaestusButton.setFont(new Font("Arial", Font.BOLD, 14));
        oHephaestusButton.setForeground(Color.white);
        oHephaestusButton.setBackground(new Color(0, 128, 0)); // Dark green
        oHephaestusButton.setFocusPainted(false);
        oHephaestusButton.addActionListener(this);
        gradientPanel.add(oHephaestusButton);

        JButton oAthenaButton = new JButton("Buy O' Athena (75 XP)");
        oAthenaButton.setBounds(20, 130, 300, 50);
        oAthenaButton.setFont(new Font("Arial", Font.BOLD, 14));
        oAthenaButton.setForeground(Color.white);
        oAthenaButton.setBackground(new Color(0, 128, 128)); // Dark cyan
        oAthenaButton.setFocusPainted(false);
        oAthenaButton.addActionListener(this);
        gradientPanel.add(oAthenaButton);

        JButton oApolloButton = new JButton("Buy O' Apollo (50 XP)");
        oApolloButton.setBounds(20, 200, 300, 50);
        oApolloButton.setFont(new Font("Arial", Font.BOLD, 14));
        oApolloButton.setForeground(Color.white);
        oApolloButton.setBackground(new Color(128, 0, 128)); // Dark purple
        oApolloButton.setFocusPainted(false);
        oApolloButton.addActionListener(this);
        gradientPanel.add(oApolloButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Buy O' Hephaestus (100 XP)":
                if (Constant.getPlayerXP() >= 100) {
                    // Perform the purchase
                    Constant.setPlayerXP(Constant.getPlayerXP() - 100);
                    // Implement the action for O' Hephaestus
                    // For example:
                    // gameFrame.activateOhephaestus();
                    newImpact = true;
                    newImpactX = epsilon.getCenterX();
                    newImpactY = epsilon.getCenterY();
                    Constant.setOpenStore(false);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Hephaestus!");
                    Constant.setOpenStore(false);
                }
                break;

            case "Buy O' Athena (75 XP)":
                if (Constant.getPlayerXP() >= 75) {
                    // Perform the purchase
                    Constant.setPlayerXP(Constant.getPlayerXP() - 75);
                    // Implement the action for O' Athena
                    // For example:
                    // gameFrame.activateOAthena();
                    epsilon.setStartOfAthena(System.currentTimeMillis());
                    Constant.setOpenStore(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Athena!");
                    Constant.setOpenStore(false);
                }
                break;

            case "Buy O' Apollo (50 XP)":
                if (Constant.getPlayerXP() >= 50) {
                    // Perform the purchase
                    Constant.setPlayerXP(Constant.getPlayerXP() - 50);
                    // Implement the action for O' Apollo
                    // For example:
                    // gameFrame.activateOApollo();
                    epsilon.setHp(Math.min(epsilon.getHp() + 10, 100));
                    Constant.setOpenStore(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Apollo!");
                    Constant.setOpenStore(false);
                }
                break;
        }
        // Update XP label
        xpLabel.setText("Your XP: " + Constant.getPlayerXP());
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
    public void setXpLabel(){
        xpLabel.setText("Your XP: " + Constant.getPlayerXP());
    }
}
