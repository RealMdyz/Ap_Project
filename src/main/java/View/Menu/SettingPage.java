package View.Menu;

import Models.Constant;
import MyProject.MyProjectData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class SettingPage extends JFrame implements ActionListener{

    private JSlider levelSlider;
    private JSlider sensitivitySlider;
    private JSlider volumeSlider;
    private JButton okButton;
    Constant constant;
    private JButton keyBindingButton;


    public SettingPage(Constant constant){
        this.constant = constant;
        this.setSize(650, 700);
        this.setLayout(new GridLayout(5, 1));
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

//        constant.setKeyMap(new HashMap<>());
//        constant.getKeyMap().put("W", KeyEvent.VK_W);
//        constant.getKeyMap().put("A", KeyEvent.VK_A);
//        constant.getKeyMap().put("S", KeyEvent.VK_S);
//        constant.getKeyMap().put("D", KeyEvent.VK_D);
//        constant.getKeyMap().put("Q", KeyEvent.VK_Q);
//        constant.getKeyMap().put("E", KeyEvent.VK_E);
//        constant.getKeyMap().put("R", KeyEvent.VK_R);
//        constant.getKeyMap().put("T", KeyEvent.VK_T);
//        constant.getKeyMap().put("Y", KeyEvent.VK_Y);
//        constant.getKeyMap().put("U", KeyEvent.VK_U);
//        constant.getKeyMap().put("I", KeyEvent.VK_I);
//        constant.getKeyMap().put("O", KeyEvent.VK_O);
//        constant.getKeyMap().put("P", KeyEvent.VK_P);
//        constant.getKeyMap().put("F", KeyEvent.VK_F);
//        constant.getKeyMap().put("G", KeyEvent.VK_G);
//        constant.getKeyMap().put("H", KeyEvent.VK_H);
//        constant.getKeyMap().put("J", KeyEvent.VK_J);
//        constant.getKeyMap().put("K", KeyEvent.VK_K);
//        constant.getKeyMap().put("L", KeyEvent.VK_L);
//        constant.getKeyMap().put("Z", KeyEvent.VK_Z);
//        constant.getKeyMap().put("X", KeyEvent.VK_X);
//        constant.getKeyMap().put("C", KeyEvent.VK_C);
//        constant.getKeyMap().put("V", KeyEvent.VK_V);
//        constant.getKeyMap().put("B", KeyEvent.VK_B);
//        constant.getKeyMap().put("N", KeyEvent.VK_N);
//        constant.getKeyMap().put("M", KeyEvent.VK_M);
//
//        constant.getKeyMap().put("Arrow Up", KeyEvent.VK_UP);
//        constant.getKeyMap().put("Arrow Down", KeyEvent.VK_DOWN);
//        constant.getKeyMap().put("Arrow Left", KeyEvent.VK_LEFT);
//        constant.getKeyMap().put("Arrow Right", KeyEvent.VK_RIGHT);


        keyBindingButton = new JButton("Key Binding Settings");
        keyBindingButton.setFont(MyProjectData.getProjectData().getFont35());
        keyBindingButton.setBackground(Color.lightGray);
        keyBindingButton.setForeground(Color.BLACK);
        keyBindingButton.addActionListener(this);
        this.add(keyBindingButton);


        this.setVisible(true);
        okButton.addActionListener(this);

    }
    private JComboBox<String> createComboBox(String label, String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0); // Set default selection
        return comboBox;
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
        else if (e.getSource() == keyBindingButton) {
            openKeyBindingSettings(); // Call a method to open the key binding settings panel
        }
    }
    private void openKeyBindingSettings() {
        JFrame frame = new JFrame("Key Binding Settings");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new KeyBindingSettingsPanel(constant));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
