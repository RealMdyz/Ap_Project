package View.Menu;

import Models.Constant;
import MyProject.MyProjectData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPage extends JFrame implements ActionListener{

    private JSlider levelSlider;
    private JSlider sensitivitySlider;
    private JSlider volumeSlider;
    private JButton okButton;
    Constant constant;

    public SettingPage(Constant constant){
        this.constant = constant;
        this.setSize(650, 700);
        this.setLayout(new GridLayout(4, 1));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setBackground(Color.lightGray);

        // Create sliders
        levelSlider = createSlider(0, 100, Constant.getLevel());
        sensitivitySlider = createSlider(0, 100, Constant.getSensitivityForMoves());
        volumeSlider = createSlider(0, 100, Constant.getSound());

        // Set slider labels
        levelSlider.setLabelTable(levelSlider.createStandardLabels(20));
        levelSlider.setFont(MyProjectData.getProjectData().getFont20());
        levelSlider.setPaintLabels(true);

        sensitivitySlider.setLabelTable(sensitivitySlider.createStandardLabels(20));
        sensitivitySlider.setFont(MyProjectData.getProjectData().getFont20());
        sensitivitySlider.setPaintLabels(true);


        volumeSlider.setLabelTable(volumeSlider.createStandardLabels(20));
        volumeSlider.setFont(MyProjectData.getProjectData().getFont20());
        volumeSlider.setPaintLabels(true);


        // Add sliders to the frame
        JPanel levelPanel = createSliderPanel("Level:", levelSlider);
        JPanel sensitivityPanel = createSliderPanel("Sensitivity for Moves:", sensitivitySlider);
        JPanel volumePanel = createSliderPanel("Volume:", volumeSlider);

        okButton = new JButton("OK");
        okButton.setBounds(0, 0, 50, 50);
        okButton.setFont(MyProjectData.getProjectData().getFont35());
        okButton.setBackground(Color.lightGray);
        okButton.setForeground(Color.BLACK);
        this.add(levelPanel);
        this.add(sensitivityPanel);
        this.add(volumePanel);
        this.add(okButton);

        this.setVisible(true);
        okButton.addActionListener(this);

    }
    private JSlider createSlider(int min, int max, int initialValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initialValue);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Set track color
        slider.setBackground(Color.lightGray);
        // Set tick color
        slider.setForeground(Color.BLACK);

        return slider;
    }


    private JPanel createSliderPanel(String label, JSlider slider) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel(label);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        return panel;
    }


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public JSlider getLevelSlider() {
        return levelSlider;
    }

    public void setLevelSlider(JSlider levelSlider) {
        this.levelSlider = levelSlider;
    }

    public JSlider getSensitivitySlider() {
        return sensitivitySlider;
    }

    public void setSensitivitySlider(JSlider sensitivitySlider) {
        this.sensitivitySlider = sensitivitySlider;
    }

    public JSlider getVolumeSlider() {
        return volumeSlider;
    }

    public void setVolumeSlider(JSlider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton){
            this.setVisible(false);
            Constant.setLevel(levelSlider.getValue());
            Constant.setSound(volumeSlider.getValue());
            Constant.setSensitivityForMoves(sensitivitySlider.getValue());
        }
    }
}
