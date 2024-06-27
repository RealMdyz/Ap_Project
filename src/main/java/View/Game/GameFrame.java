package View.Game;

import Models.Constant;
import Models.ObjectsInGame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    Constant constant;
    JPanel gamePanel;
    private JButton exitButton;
    private boolean isometric, solb;

    public GameFrame(Constant constant, int height, int width, boolean isometric, boolean solb){
        this.isometric = isometric;
        this.solb = solb;
        this.constant = constant;
        this.setTitle("Window Kill");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.repaint();

        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(height, width);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        gamePanel = new JPanel();
        gamePanel.setBounds(0,0, Constant.FIRST_WIDTH * 2, Constant.FIRST_WIDTH * 2);
        gamePanel.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.requestFocusInWindow();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(Color.black);
        setContentPane(gamePanel);

        exitButton = new JButton("exit");
        exitButton.setBounds(340, 0, 70, 30);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.black);
        exitButton.setOpaque(false);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Constant.setIsRunning(false);
            }
        });
        gamePanel.repaint();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
    }
    public JPanel getGamePanel() {
        return gamePanel;
    }
    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void addToGamePanel(ObjectsInGame object) {
        gamePanel.add(object);
        // Update the layout of gamePanel to reflect the new object
        gamePanel.revalidate();
        // Repaint gamePanel to reflect the changes
        gamePanel.repaint();
    }
    public void removeFromGamePanel(ObjectsInGame object) {
        gamePanel.remove(object);
        // Update the layout of gamePanel to reflect the new object
        gamePanel.revalidate();
        // Repaint gamePanel to reflect the changes
        gamePanel.repaint();
    }
    public void addJPanel(JPanel jPanel){
        gamePanel.add(jPanel, BorderLayout.NORTH);
        // Update the layout of gamePanel to reflect the new object
        gamePanel.revalidate();
        // Repaint gamePanel to reflect the changes
        gamePanel.repaint();
    }
    public void removeJPanel(JPanel jPanel){
        gamePanel.remove(jPanel);
        // Update the layout of gamePanel to reflect the new object
        gamePanel.revalidate();
        // Repaint gamePanel to reflect the changes
        gamePanel.repaint();
    }
    public void addExitButtonToThisFrame(){
        gamePanel.add(exitButton);
    }

    public boolean isIsometric() {
        return isometric;
    }

    public void setIsometric(boolean isometric) {
        this.isometric = isometric;
    }

    public boolean isSolb() {
        return solb;
    }

    public void setSolb(boolean solb) {
        this.solb = solb;
    }

}
