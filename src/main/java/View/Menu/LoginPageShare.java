package View.Menu;

import Controller.GameLoop;
import Models.Constant;
import Models.Game;
import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LoginPageShare extends JFrame implements ActionListener {
    JButton startButton;
    JButton settingButton;
    JButton tutorialButton;
    JButton skillTreeButton;
    JButton exitButton;
    JLayeredPane backgroundPanel;
    JLabel backgroundImageLabel;

    protected Constant constant;
    public LoginPageShare(Constant constant){
        this.constant = constant;

        ImageIcon backgroundImage = MyProjectData.getProjectData().getGameMenuImage();
        ImageIcon gameIcon = MyProjectData.getProjectData().getGameIcon();
        Font font20 = MyProjectData.getProjectData().getFont20();
        Font font10 =  MyProjectData.getProjectData().getFont10();
        this.setSize(650, 700);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setIconImage(gameIcon.getImage());

        backgroundPanel = new JLayeredPane();
        backgroundPanel.setBounds(0, 0, 700, 700);

        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 700, 700);
        backgroundImageLabel.setOpaque(true);

        startButton = new JButton("Start");
        startButton.setBounds(100, 100, 120, 50);
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.setFocusable(false);
        startButton.setFont(font20);

        settingButton = new JButton("Settings");
        settingButton.setBounds(80, 200, 120, 50);
        settingButton.setBackground(Color.RED);
        settingButton.setForeground(Color.BLACK);
        settingButton.setFocusable(false);
        settingButton.setFont(font20);

        tutorialButton = new JButton("Tutorial");
        tutorialButton.setBounds(400, 500, 120, 50);
        tutorialButton.setBackground(Color.RED);
        tutorialButton.setForeground(Color.BLACK);
        tutorialButton.setFocusable(false);
        tutorialButton.setFont(font20);

        skillTreeButton = new JButton("Skill Tree");
        skillTreeButton.setBounds(250, 350, 150, 50);
        skillTreeButton.setBackground(Color.RED);
        skillTreeButton.setForeground(Color.BLACK);
        skillTreeButton.setFocusable(false);
        skillTreeButton.setFont(font20);

        exitButton = new JButton("Exit");
        exitButton.setBounds(0, 0, 70, 40);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.BLACK);
        exitButton.setFocusable(false);
        exitButton.setFont(font10);

        startButton.addActionListener(this);
        settingButton.addActionListener(this);
        tutorialButton.addActionListener(this);
        skillTreeButton.addActionListener(this);
        exitButton.addActionListener(this);

        backgroundPanel.add(backgroundImageLabel, Integer.valueOf(0));
        backgroundPanel.add(startButton, Integer.valueOf(1));
        backgroundPanel.add(settingButton, Integer.valueOf(1));
        backgroundPanel.add(skillTreeButton, Integer.valueOf(1));
        backgroundPanel.add(tutorialButton, Integer.valueOf(1));
        backgroundPanel.add(exitButton, Integer.valueOf(1));

        this.add(backgroundPanel);
        repaint();
    }
    private void Start() {
        Game game = new Game(constant);
        GameLoop gameLoop = new GameLoop(game, constant);
        game.getGameFrame().setVisible(true);
        gameLoop.start();
        repaint();
    }
    private void Setting(){
        SettingPage settingPage = new SettingPage(constant);
        //System.out.println(Constant.getLevel() + " " + Constant.getSound() + " " +  Constant.getSensitivityForMoves());
        repaint();
    }
    private void Tutorial(){

    }

    private void SkillTree(){
            SkillTreePanel skillTreePanel = new SkillTreePanel(this, constant);
            skillTreePanel.setPreferredSize(new Dimension(650, 700)); // Set preferred size
            skillTreePanel.setVisible(true);
            backgroundPanel.add(skillTreePanel, Integer.valueOf(2)); // Add with a higher layer number
            repaint();
    }


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
           // this.setVisible(false);
            Start();
        }
        if(e.getSource() == settingButton){
            Setting();
        }
        if(e.getSource() == tutorialButton){
            this.setVisible(false);
            Tutorial();
        }
        if(e.getSource() == skillTreeButton){
            SkillTree();
        }
        if(e.getSource() == exitButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
