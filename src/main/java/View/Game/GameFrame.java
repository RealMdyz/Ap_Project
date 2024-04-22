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
    Epsilon epsilon;
    private JButton exitButton;
    TopPanel topPanel;
    protected ArrayList<Shot> shots = new ArrayList<>();
    protected ArrayList<Collectible>  collectibles = new ArrayList<>();
    public GameFrame(Constant constant, Epsilon epsilon,TopPanel topPanel ){
        this.topPanel = topPanel;
        this.constant = constant;
        this.epsilon = epsilon;
        this.setTitle("Window Kill");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.repaint();

        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(Constant.FIRST_WIDTH, Constant.FIRST_HEIGHT);
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
        panel.add(epsilon);
        gamePanel.repaint();

        panel.add(topPanel, BorderLayout.NORTH);
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
    public void removeOneShot(Shot shot){
        gamePanel.remove(shot);
        shots.remove(shot);
        gamePanel.revalidate();
        gamePanel.repaint();

    }
    public void addToGamePanel(ObjectsInGame object){
        gamePanel.add(object);
        gamePanel.repaint();
       // System.out.println("Hello");
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

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }
    public void setCollectibles(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
    }
    public void addNewCollectoble(Enemy enemy){
        if(enemy.getCollectibleNumber() == 1){
            Collectible collectible = new Collectible(enemy.getX(), enemy.getY(), 0, 5, System.currentTimeMillis());
            gamePanel.add(collectible);
            collectibles.add(collectible);
        }
        else if(enemy.getCollectibleNumber() == 2){
            Collectible collectible = new Collectible(enemy.getX(), enemy.getY(), 0, 5, System.currentTimeMillis());
            gamePanel.add(collectible);
            collectibles.add(collectible);
            Collectible collectible1 = new Collectible(enemy.getX() + 50, enemy.getY() + 50, 0, 5, System.currentTimeMillis());
            gamePanel.add(collectible1);
            collectibles.add(collectible1);
        }
    }
    public void checkTheCollectibleTime(){
        Collectible collectible = new Collectible(0, 0, 0, 5, System.currentTimeMillis());
        for (Collectible collectible1 : collectibles){
            if(System.currentTimeMillis() - collectible1.getStart() > 5000){
                collectible = collectible1;
                break;
            }
        }
        gamePanel.remove(collectible);
        collectibles.remove(collectible);
        gamePanel.repaint();

    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    public void addToGamePanelAnObject(ObjectsInGame object) {
        gamePanel.add(object);
        // Update the layout of gamePanel to reflect the new object
        gamePanel.revalidate();
        // Repaint gamePanel to reflect the changes
        gamePanel.repaint();
    }

}
