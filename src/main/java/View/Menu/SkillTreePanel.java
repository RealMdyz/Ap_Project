package View.Menu;
import Models.Constant;
import Models.Epsilon.Epsilon;
import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SkillTreePanel extends JPanel implements ActionListener {

    private JFrame parentPanel;
    private BufferedImage writOfAcesoImage;
    private BufferedImage writOfAresImage;
    private BufferedImage writOfProteusImage;
    Constant constant;
    JLabel moneyLabel;

    public SkillTreePanel(JFrame parentPanel, Constant constant) {
        this.parentPanel = parentPanel;
        this.constant = constant;
        this.setSize(650, 700);
        setLayout(new BorderLayout()); // Use BorderLayout for better component arrangement
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        writOfAcesoImage = MyProjectData.getProjectData().getWritOfAceso();
        writOfAresImage = MyProjectData.getProjectData().getWritOfAres();
        writOfProteusImage = MyProjectData.getProjectData().getWritOfProteus();

        JPanel skillButtonsPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Panel for skill buttons
        skillButtonsPanel.setBackground(new Color(240, 240, 240)); // Set background color

        JLabel titleLabel = new JLabel("Skill Tree", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));

        JButton writOfAresButton = createStyledButton("Writ of Ares", resizeIcon(new ImageIcon(writOfAresImage), 80, 80));
        JButton writOfAcesoButton = createStyledButton("Writ of Aceso", resizeIcon(new ImageIcon(writOfAcesoImage), 80, 80));
        JButton writOfProteusButton = createStyledButton("Writ of Proteus", resizeIcon(new ImageIcon(writOfProteusImage), 80, 80));

        writOfAresButton.addActionListener(this);
        writOfAcesoButton.addActionListener(this);
        writOfProteusButton.addActionListener(this);

        writOfAresButton.setText("Writ of Ares");
        writOfAcesoButton.setText("Writ of Aceso");
        writOfProteusButton.setText("Writ of Proteus");

        int xpForWritOfAres = 750;
        int xpForWritOfAceso = 500;
        int xpForWritOfProteus = 1000;

        // Create JLabels to display XP information under each button
        JLabel xpLabelAres = new JLabel("(" + xpForWritOfAres + " XP)");
        JLabel xpLabelAceso = new JLabel("(" + xpForWritOfAceso + " XP)");
        JLabel xpLabelProteus = new JLabel("(" + xpForWritOfProteus + " XP)");

        // Set font and foreground color for XP labels
        xpLabelAres.setFont(MyProjectData.getProjectData().getFont35());
        xpLabelAceso.setFont(MyProjectData.getProjectData().getFont35());
        xpLabelProteus.setFont(MyProjectData.getProjectData().getFont35());

        skillButtonsPanel.add(writOfAresButton);
        skillButtonsPanel.add(xpLabelAres);
        skillButtonsPanel.add(writOfAcesoButton);
        skillButtonsPanel.add(xpLabelAceso);
        skillButtonsPanel.add(writOfProteusButton);
        skillButtonsPanel.add(xpLabelProteus);;

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for OK button and money panel
        bottomPanel.setBackground(new Color(240, 240, 240));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(this); // Add ActionListener for OK button

        moneyLabel = new JLabel("XP : " + Constant.getSavedXp()); // Example money panel
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 18));

        bottomPanel.add(okButton);
        bottomPanel.add(moneyLabel);

        add(titleLabel, BorderLayout.NORTH);
        add(skillButtonsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
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
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String buttonText = button.getText();
            if(buttonText.equals("Writ of Ares")){
                if(Constant.getSavedXp() >= 750){
                    Constant.setSavedXp(Constant.getSavedXp() - 750);
                    Epsilon.setLevelOfWritOfAres(Epsilon.getLevelOfWritOfAres() + 2);
                }
            }
            else if(buttonText.equals("Writ of Aceso")){
                if(Constant.getSavedXp() >= 500){
                    Constant.setSavedXp(Constant.getSavedXp() - 500);
                    Epsilon.setWriteOfAceso(true);
                }
            }
            else if(buttonText.equals("Writ of Proteus")){
                if(Constant.getSavedXp() >= 1000){
                    Constant.setSavedXp(Constant.getSavedXp() - 1000);
                    Epsilon.setLevelOfWritOfProteus(Epsilon.getLevelOfWritOfProteus() + 1);
                }
            }
             else if (buttonText.equals("OK")) {
                // Implement action for the OK button
                // For now, just print a message
                this.setVisible(false);
            }
            //System.out.println(Constant.getSavedXp());
            File file = new File("gameData");
            int savedXp = Constant.getSavedXp();
            int levelOfWritOfAres = Epsilon.getLevelOfWritOfAres();
            int levelOfWritOfProteus = Epsilon.getLevelOfWritOfProteus();
            int lForAceso = (Epsilon.isWriteOfAceso() ? 1 : 0);
            constant.writeInFile(savedXp + Constant.getPlayerXP(), levelOfWritOfAres, lForAceso, levelOfWritOfProteus);
            constant.updateToSkillAndXp();
        }
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    private void updateMoneyLabel(){
        moneyLabel.setText("XP : " + Constant.getSavedXp());
    }
}
