package View.Menu;

import Models.Constant;
import MyProject.MyProjectData;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SkillTreePanel extends JPanel implements ActionListener {
    private JFrame parentPanel;
    private Constant constant;
    private JLabel moneyLabel;
    int levelOfAttack, levelOfDefend, levelOfChangeShape, savedXp;

    protected BufferedImage writOfAceso = MyProjectData.getProjectData().getWritOfAceso();
    protected BufferedImage writOfAres = MyProjectData.getProjectData().getWritOfAres();
    protected BufferedImage writOfProteus = MyProjectData.getProjectData().getWritOfProteus();
    protected BufferedImage writOfAstrape = MyProjectData.getProjectData().getWritOfAstrape();
    protected BufferedImage writOfCerberus = MyProjectData.getProjectData().getWritOfCerberus();
    protected BufferedImage writOfChiron = MyProjectData.getProjectData().getWritOfChiron();
    protected BufferedImage writOfDolus = MyProjectData.getProjectData().getWritOfDolus();
    protected BufferedImage writOfEmpusa = MyProjectData.getProjectData().getWritOfEmpusa();
    protected BufferedImage writOfMelampus = MyProjectData.getProjectData().getWritOfMelampus();

    private Map<String, Boolean> skillStatus = new HashMap<>();  // To store the status of each skill
    private Map<String, String> levelSkill = new HashMap<>();

    public SkillTreePanel(JFrame parentPanel, Constant constant) {
        this.parentPanel = parentPanel;
        this.constant = constant;
        this.setSize(650, 700);
        setLayout(new BorderLayout()); // Use BorderLayout for better component arrangement
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        loadSkillStatus(); // Load the skill status from file or initialize

        JLabel titleLabel = createTitleLabel();
        JPanel skillButtonsPanel = createSkillButtonsPanel();
        JPanel bottomPanel = createBottomPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(skillButtonsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Skill Tree", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));
        return titleLabel;
    }

    private JPanel createSkillButtonsPanel() {
        JPanel skillButtonsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        skillButtonsPanel.setBackground(new Color(240, 240, 240));

        // Attack Skill Buttons
        addSkillButton(skillButtonsPanel, "Writ of Ares", writOfAres, 750);
        addSkillButton(skillButtonsPanel, "Writ of Astrape", writOfAstrape, 1000);
        addSkillButton(skillButtonsPanel, "Writ of Cerberus", writOfCerberus, 1500);

        // Defense Skill Buttons
        addSkillButton(skillButtonsPanel, "Writ of Aceso", writOfAceso, 500);
        addSkillButton(skillButtonsPanel, "Writ of Melampus", writOfMelampus, 750);
        addSkillButton(skillButtonsPanel, "Writ of Chiron", writOfChiron, 900);

        // Shape Shift Skill Buttons
        addSkillButton(skillButtonsPanel, "Writ of Proteus", writOfProteus, 1000);
        addSkillButton(skillButtonsPanel, "Writ of Empusa", writOfEmpusa, 750);
        addSkillButton(skillButtonsPanel, "Writ of Dolus", writOfDolus, 1500);

        return skillButtonsPanel;
    }

    private void addSkillButton(JPanel panel, String text, BufferedImage image, int xp) {
        JButton skillButton = createStyledButton(text, resizeIcon(new ImageIcon(image), 80, 80));
        skillButton.addActionListener(this);
        panel.add(skillButton);
        JLabel xpLabel = new JLabel("(" + xp + " XP)");
        xpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        xpLabel.setForeground(Color.BLACK);
        panel.add(xpLabel);

        if (!isSkillUnlocked(text)) {
            skillButton.setEnabled(false);
        }
    }


    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(240, 240, 240));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);

        moneyLabel = new JLabel("XP : " + Constant.getSavedXp());
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 18));

        bottomPanel.add(okButton);
        bottomPanel.add(moneyLabel);

        return bottomPanel;
    }

    private JButton createStyledButton(String text, ImageIcon icon) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setIcon(icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adjust button padding

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        loadData();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String buttonText = button.getText();
            switch (buttonText) {
                case "Writ of Ares":
                    if(unlockSkill("Writ of Ares", 750)){
                        levelOfAttack = 1;
                    }
                    break;
                case "Writ of Astrape":
                    if (isSkillUnlocked("Writ of Ares")) {
                        if(unlockSkill("Writ of Astrape", 1000)){
                            levelOfAttack = 2;
                        }
                    } else {
                        showError("You need to unlock Writ of Ares first.");
                    }
                    break;
                case "Writ of Cerberus":
                    if (isSkillUnlocked("Writ of Astrape")) {
                        if(unlockSkill("Writ of Cerberus", 1500)){
                            levelOfAttack = 3;
                        }
                    } else {
                        showError("You need to unlock Writ of Astrape first.");
                    }
                    break;
                case "Writ of Aceso":
                    if(unlockSkill("Writ of Aceso", 500)){
                        levelOfDefend = 1;
                    }
                    break;
                case "Writ of Melampus":
                    if (isSkillUnlocked("Writ of Aceso")) {
                        if(unlockSkill("Writ of Melampus", 750)){
                            levelOfDefend = 2;
                        }
                    } else {
                        showError("You need to unlock Writ of Aceso first.");
                    }
                    break;
                case "Writ of Chiron":
                    if (isSkillUnlocked("Writ of Melampus")) {
                        if(unlockSkill("Writ of Chiron", 900)){
                            levelOfDefend = 3;
                        }
                    } else {
                        showError("You need to unlock Writ of Melampus first.");
                    }
                    break;
                case "Writ of Proteus":
                    if(unlockSkill("Writ of Proteus", 1000)){
                        levelOfChangeShape = 1;
                    }
                    break;
                case "Writ of Empusa":
                    if (isSkillUnlocked("Writ of Proteus")) {
                        if(unlockSkill("Writ of Empusa", 750)){
                            levelOfChangeShape = 2;
                        }
                    } else {
                        showError("You need to unlock Writ of Proteus first.");
                    }
                    break;
                case "Writ of Dolus":
                    if (isSkillUnlocked("Writ of Empusa")) {
                        if(unlockSkill("Writ of Dolus", 1500)){
                            levelOfChangeShape = 3;
                        }
                    } else {
                        showError("You need to unlock Writ of Empusa first.");
                    }
                    break;
                case "OK":
                    this.setVisible(false);
                    break;
            }
            updateMoneyLabel();
            constant.writeInFile(Constant.getSavedXp(), levelOfAttack, levelOfDefend, levelOfChangeShape);
        }
    }

    private boolean unlockSkill(String skill, int xpCost) {
        if (Constant.getSavedXp() >= xpCost) {
            Constant.setSavedXp(Constant.getSavedXp() - xpCost);
            skillStatus.remove(levelSkill.get(skill));
            skillStatus.put(levelSkill.get(skill), true); // Mark the skill as unlocked
            setButtonEnabled(levelSkill.get(skill), true);
            // Save the skill status
            repaint();
            return true;
        }
        else{
            showError("Not Enough Xp!");
            return false;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isSkillUnlocked(String skill) {
        return skillStatus.get(skill);
    }

    private void updateMoneyLabel() {
        moneyLabel.setText("XP : " + Constant.getSavedXp());
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    private void loadSkillStatus(){
        File file = new File("gameData");

        levelSkill.put("Writ of Ares", "Writ of Astrape");
        levelSkill.put("Writ of Astrape", "Writ of Cerberus");
        levelSkill.put("Writ of Cerberus", "Writ of Cerberus");

        levelSkill.put("Writ of Aceso", "Writ of Melampus");
        levelSkill.put("Writ of Melampus", "Writ of Chiron");
        levelSkill.put("Writ of Chiron", "Writ of Chiron");

        levelSkill.put("Writ of Proteus", "Writ of Empusa");
        levelSkill.put("Writ of Empusa", "Writ of Dolus");
        levelSkill.put("Writ of Dolus", "Writ of Dolus");

        skillStatus.put("Writ of Ares", true);
        skillStatus.put("Writ of Astrape", false);
        skillStatus.put("Writ of Cerberus", false);
        skillStatus.put("Writ of Aceso", true);
        skillStatus.put("Writ of Melampus", false);
        skillStatus.put("Writ of Chiron", false);
        skillStatus.put("Writ of Proteus", true);
        skillStatus.put("Writ of Empusa", false);
        skillStatus.put("Writ of Dolus", false);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            savedXp = scanner.nextInt();
            levelOfAttack = (scanner.nextInt());
            levelOfDefend = (scanner.nextInt());
            levelOfChangeShape = scanner.nextInt();
            String start = "Writ of Ares";
            while (levelOfAttack >= 0){
                levelOfAttack -= 1;
                skillStatus.put(start, true);
                start = levelSkill.get(start);
            }
            start = "Writ of Aceso";
            while (levelOfDefend >= 0){
                levelOfDefend -= 1;
                skillStatus.put(start, true);
                start = levelSkill.get(start);
            }
            start = "Writ of Proteus";
            while (levelOfChangeShape >= 0){
                levelOfChangeShape -= 1;
                skillStatus.put(start, true);
                start = levelSkill.get(start);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setButtonEnabled(String key, boolean enabled) {
        Component[] components = this.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] buttons = panel.getComponents();
                for (Component btn : buttons) {
                    if (btn instanceof JButton) {
                        JButton button = (JButton) btn;
                        if (button.getText().equals(key)) {
                            button.setEnabled(enabled);
                            return; // Assuming each button text is unique, we can exit after finding the match
                        }
                    }
                }
            }
        }
        System.out.println("Button with key '" + key + "' not found.");
    }
    private void loadData(){
        File file = new File("gameData");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            savedXp = scanner.nextInt();
            levelOfAttack = (scanner.nextInt());
            levelOfDefend = (scanner.nextInt());
            levelOfChangeShape = scanner.nextInt();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
