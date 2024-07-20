package View.Menu;

import Models.AttackType;
import Models.Constant;
import Models.EntityType;
import Models.Epsilon.Epsilon;
import Models.Games.CheckTheStateOfTheGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckPointPanel extends JPanel implements ActionListener {
    private JLabel xpLabel;
    private JLabel prLabel;
    Epsilon epsilon;
    private long pr;

    public CheckPointPanel(Epsilon epsilon) {
        this.epsilon = epsilon;
        this.setSize(650, 700);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = createTitleLabel();
        JPanel buttonPanel = createButtonPanel();
        JPanel bottomPanel = createBottomPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("SaveGame", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));
        return titleLabel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton saveButton = new JButton("SaveGame");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        JButton rewardButton = new JButton("Get (10% PR)");
        rewardButton.setFont(new Font("Arial", Font.BOLD, 18));
        rewardButton.setForeground(Color.WHITE);
        rewardButton.setBackground(new Color(70, 130, 180));
        rewardButton.setFocusPainted(false);
        rewardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rewardButton.addActionListener(this);
        buttonPanel.add(rewardButton);

        return buttonPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(240, 240, 240));

        xpLabel = new JLabel("XP : " + Constant.getPlayerXP());
        xpLabel.setFont(new Font("Arial", Font.BOLD, 18));

        prLabel = new JLabel("PR : " + 0); // فرض کنید که یک متد getPlayerPR در کلاس Constant وجود دارد.
        prLabel.setFont(new Font("Arial", Font.BOLD, 18));

        bottomPanel.add(xpLabel);
        bottomPanel.add(prLabel); // اضافه کردن prLabel به پانل

        return bottomPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "SaveGame":
                saveGame();
                break;
            case "Get (10% PR)":
                getReward();
                break;
        }

    }

    private void saveGame() {
        int xpCost = (int)pr;
        if (Constant.getPlayerXP() >= pr) {
            Constant.setPlayerXP(Constant.getPlayerXP() - xpCost);
            epsilon.reduceHp(-10, AttackType.REDUCE_FOR_INCREASE, EntityType.NOF_FOUND);
            JOptionPane.showMessageDialog(this, "Game Saved And Your Hp is increase by 10");
        } else {
            JOptionPane.showMessageDialog(this, "You Don't Have Enough Xp !", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.setVisible(false);
        Constant.setOpenCheckPointPanel(false);

    }

    private void getReward() {
        int prReward = (int) (0.1 * pr); // محاسبه 10% PR
        Constant.setPlayerXP(prReward + Constant.getPlayerXP());
        JOptionPane.showMessageDialog(this, "You Get " + prReward + "Xp.");
        this.setVisible(false);
        Constant.setOpenCheckPointPanel(false);
        CheckTheStateOfTheGame.haveACheckPoint = 0;
    }

    public void updateXpLabel(long pr) {
        this.pr = pr;
        xpLabel.setText("XP : " + Constant.getPlayerXP());
        prLabel.setText("PR : " + pr);
    }

}
