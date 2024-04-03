package View.Game;

import Models.Constant;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    Constant constant;
    JPanel panel;
    JPanel gamePanel;
    Epsilon epsilon;
    private JButton exitButton;
    protected ArrayList<Shot> shots = new ArrayList<>();
    public GameFrame(Constant constant, Epsilon epsilon){
        this.constant = constant;
        this.epsilon = epsilon;
        this.setSize(Constant.FIRST_WIDTH, Constant.FIRST_HEIGHT);
        this.setResizable(false);
        this.setTitle("Window Kill");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.repaint();

        panel = new JPanel();
        panel.setBounds(0, 0, Constant.FIRST_WIDTH, Constant.FIRST_HEIGHT);
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.requestFocusInWindow();
        panel.setLayout(null);
        panel.setBackground(Color.black);
        setContentPane(panel);

        gamePanel = new JPanel();
        gamePanel.setBounds(0,0, Constant.FIRST_WIDTH, Constant.FIRST_HEIGHT);
        gamePanel.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.requestFocusInWindow();
        gamePanel.setLayout(null);
        gamePanel.setOpaque(false);

        exitButton = new JButton("exit");
        exitButton.setBounds(0, 0, 30, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constant.setIsRunning(false);
            }
        });
       //gamePanel.add(exitButton);
        panel.add(gamePanel);
        gamePanel.add(epsilon);
        gamePanel.repaint();
    }
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Epsilon epsilon) {
        this.epsilon = epsilon;
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }
}
