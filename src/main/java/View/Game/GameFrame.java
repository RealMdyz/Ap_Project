package View.Game;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Epsilon.Collectible;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Menu.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    Constant constant;
    JPanel panel;
    JPanel gamePanel;
    private JButton exitButton;
    public GameFrame(Constant constant, int height, int width){
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


        panel = new JPanel();
        panel.setBounds(0, 0, Constant.FIRST_WIDTH * 2, Constant.FIRST_HEIGHT * 2);
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.requestFocusInWindow();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);
        setContentPane(panel);

        gamePanel = new JPanel();
        gamePanel.setBounds(0,0, Constant.FIRST_WIDTH * 2, Constant.FIRST_WIDTH * 2);
        gamePanel.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.requestFocusInWindow();
        gamePanel.setLayout(null);
        gamePanel.setOpaque(false);

        exitButton = new JButton("exit");
        exitButton.setBounds(340, 0, 70, 30);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.black);
        exitButton.setOpaque(false);
        gamePanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Constant.setIsRunning(false);
            }
        });
        panel.add(gamePanel);
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

    public void removeShotFromPanel(Shot shot){
        gamePanel.remove(shot);
        gamePanel.revalidate();
        gamePanel.repaint();

    }
    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void addToPanel(JPanel jPanel){
        panel.add(jPanel, BorderLayout.NORTH);
    }
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
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

}
