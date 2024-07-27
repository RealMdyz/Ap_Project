package View.Menu;

import javax.swing.*;
import java.awt.*;

public class GameHistoryPage extends JFrame {
    public GameHistoryPage(String historyData) {
        setTitle("Game History");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(historyData);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}