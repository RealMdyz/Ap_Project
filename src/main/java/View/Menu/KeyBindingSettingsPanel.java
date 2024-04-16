package View.Menu;

import Models.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyBindingSettingsPanel extends JPanel implements ActionListener {

    private Map<String, Integer> keyBindings;
    private JLabel titleLabel;
    private JPanel bindingPanel;
    private JButton saveButton;
    private Constant constant;

    public KeyBindingSettingsPanel(Constant constant) {
        this.constant = constant;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        keyBindings = constant.getKeyMap();

        titleLabel = new JLabel("Key Binding Settings");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel, BorderLayout.NORTH);

        bindingPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        bindingPanel.setBackground(Color.WHITE);
        add(bindingPanel, BorderLayout.CENTER);

        saveButton = new JButton("Save Changes");
        add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(KeyBindingSettingsPanel.this, "Changes saved successfully.");

            }
        });

        populateBindingPanel();

        setPreferredSize(new Dimension(400, 300));
    }

    private void populateBindingPanel() {
        bindingPanel.removeAll();
        for (Map.Entry<String, Integer> entry : keyBindings.entrySet()) {
            JLabel actionLabel = new JLabel(entry.getKey());
            actionLabel.setForeground(Color.BLACK);
            bindingPanel.add(actionLabel);

            JButton changeButton = new JButton(KeyEvent.getKeyText(entry.getValue()));
            changeButton.setActionCommand(entry.getKey());
            changeButton.addActionListener(this);
            changeButton.setPreferredSize(new Dimension(120, 30));
            changeButton.setBackground(Color.LIGHT_GRAY);
            changeButton.setForeground(Color.BLACK);
            changeButton.setFocusPainted(false);
            changeButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            bindingPanel.add(changeButton);
        }
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JLabel actionLabel = (JLabel) ((JButton) e.getSource()).getParent().getComponent(0);

        System.out.println("Binding key for action: " + action);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                if (ke.getID() == KeyEvent.KEY_PRESSED) {
                    if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        // Cancel key binding
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
                        return false;
                    } else {
                        String pressedKeyAction = getKeyAction(ke.getKeyCode(), action);
                        if (pressedKeyAction == null) {
                            keyBindings.put(action, ke.getKeyCode());
                            JButton button = (JButton) e.getSource();
                            button.setText(KeyEvent.getKeyText(ke.getKeyCode()));
                            // Update key bindings in Constant class
                            constant.setKeyBindings(keyBindings);
                            // Update key bindings data in Constant class
                            constant.updateKeyBindings();
                            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(KeyBindingSettingsPanel.this,
                                    "This key is already bound to: " + pressedKeyAction,
                                    "Key Binding Error", JOptionPane.ERROR_MESSAGE);
                            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
                            return false;
                        }
                    }
                }
                return false;
            }
        });
    }


    // Helper method to get the action associated with a key code
    private String getKeyAction(int keyCode, String action) {
        for (Map.Entry<String, Integer> entry : keyBindings.entrySet()) {
            if (entry.getValue() == keyCode) {
                return entry.getKey();
            }
        }
        return null;
    }

}
