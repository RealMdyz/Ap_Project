package View.Game;

import Models.Constant;
import Models.Epsilon.Epsilon;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    Constant constant;
    JPanel panel;
    JPanel gamePanel;
    Epsilon epsilon;
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
}
