package View.Menu;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JLabel messageLabel;
    public MessagePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 100));
        setBackground(Color.lightGray);

        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
    }
}

