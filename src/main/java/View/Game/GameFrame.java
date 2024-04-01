package View.Game;

import Models.Constant;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    Constant constant;

    public GameFrame(){
        this.setSize(constant.FIRST_WIDTH, constant.FIRST_HEIGHT);
        this.setResizable(false);
        this.setTitle("Window Kill");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
