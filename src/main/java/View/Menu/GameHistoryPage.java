package View.Menu;

import javax.swing.*;
import java.awt.*;

public class GameHistoryPage extends JFrame {
    public GameHistoryPage(String historyData) {
        setTitle("Game History");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(historyData);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(380, 250));

        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}
