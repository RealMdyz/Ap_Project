package View.Menu;

import javax.swing.*;
import java.awt.*;

public class SquadInfoPanel extends JFrame {
    private JTextArea infoArea;

    public SquadInfoPanel(String squadInfo) {
        setTitle("Squad Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        infoArea = new JTextArea();
        infoArea.setText(squadInfo);
        infoArea.setEditable(false);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
