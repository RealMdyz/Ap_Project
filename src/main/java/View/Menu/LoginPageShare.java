package View.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageShare extends JFrame implements ActionListener {
    JButton start;
    JButton setting;
    JButton tutorial;
    JButton skillTree;
    JButton exit;

    public LoginPageShare(){
        this.setSize(650, 700);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
