package View.Menu.StorePanel;

import Models.Constant;
import Models.Epsilon.Epsilon;
import View.Menu.MessagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StorePanelGraphics extends JFrame implements ActionListener {
    Constant constant;
    JLabel xpLabel;
    StorePanelLogic storePanelLogic;
    Epsilon epsilon;

    public StorePanelGraphics(Constant constant, Epsilon epsilon){
        storePanelLogic = new StorePanelLogic(constant, epsilon);
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

        JButton oDeimosButton = new JButton("Buy O' Deimos, Dismay (120 XP)");
        oDeimosButton.setBounds(20, 270, 300, 50);
        oDeimosButton.setFont(new Font("Arial", Font.BOLD, 14));
        oDeimosButton.setForeground(Color.white);
        oDeimosButton.setBackground(new Color(128, 0, 0)); // Dark red
        oDeimosButton.setFocusPainted(false);
        oDeimosButton.addActionListener(this);
        gradientPanel.add(oDeimosButton);

        JButton oHypnosButton = new JButton("Buy O' Hypnos, Slumber (150 XP)");
        oHypnosButton.setBounds(20, 340, 300, 50);
        oHypnosButton.setFont(new Font("Arial", Font.BOLD, 14));
        oHypnosButton.setForeground(Color.white);
        oHypnosButton.setBackground(new Color(0, 0, 128)); // Dark blue
        oHypnosButton.setFocusPainted(false);
        oHypnosButton.addActionListener(this);
        gradientPanel.add(oHypnosButton);

        JButton oPhonoiButton = new JButton("Buy O' Phonoi, Slaughter (200 XP)");
        oPhonoiButton.setBounds(20, 410, 300, 50);
        oPhonoiButton.setFont(new Font("Arial", Font.BOLD, 14));
        oPhonoiButton.setForeground(Color.white);
        oPhonoiButton.setBackground(new Color(255, 165, 0)); // Orange
        oPhonoiButton.setFocusPainted(false);
        oPhonoiButton.addActionListener(this);
        gradientPanel.add(oPhonoiButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Buy O' Hephaestus (100 XP)":
                if (Constant.getPlayerXP() >= 100) {
                    Constant.setPlayerXP(Constant.getPlayerXP() - 100);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Hephaestus!");
                }
                Constant.setOpenStore(false);
                break;

            case "Buy O' Athena (75 XP)":
                if (Constant.getPlayerXP() >= 75) {
                    Constant.setPlayerXP(Constant.getPlayerXP() - 75);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Athena!");
                }
                Constant.setOpenStore(false);
                break;

            case "Buy O' Apollo (50 XP)":
                if (Constant.getPlayerXP() >= 50) {
                    Constant.setPlayerXP(Constant.getPlayerXP() - 50);
                    epsilon.setHp(epsilon.getHp() + 10);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Apollo!");
                }
                Constant.setOpenStore(false);
                break;

            case "Buy O' Deimos, Dismay (120 XP)":
                if (Constant.getPlayerXP() >= 120) {
                    Constant.setPlayerXP(Constant.getPlayerXP() - 120);
                    epsilon.getEpsilonLogic().setStartOfNonHoveringDistance(System.currentTimeMillis());
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Deimos, Dismay!");
                }
                Constant.setOpenStore(false);
                break;

            case "Buy O' Hypnos, Slumber (150 XP)":
                if (Constant.getPlayerXP() >= 150) {
                    Constant.setPlayerXP(Constant.getPlayerXP() - 150);
                    Constant.setStartOFHypnosSlumber(System.currentTimeMillis());
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Hypnos, Slumber!");
                }
                Constant.setOpenStore(false);
                break;

            case "Buy O' Phonoi, Slaughter (200 XP)":
                if (Constant.getPlayerXP() >= 200 && System.currentTimeMillis() - storePanelLogic.getLastPhonoiSlaughter() > 12000) {
                    Constant.setPlayerXP(Constant.getPlayerXP() - 200);
                    epsilon.getEpsilonLogic().setInPhonoiSlaughter(true);
                    storePanelLogic.setLastPhonoiSlaughter(System.currentTimeMillis());
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough XP to buy O' Phonoi, Slaughter!");
                }
                Constant.setOpenStore(false);
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
