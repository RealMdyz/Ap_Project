package Models.BossFight;

import Models.Constant;
import Models.Enemy.Enemy;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;

public class SmileyRightHand extends Enemy {
    public SmileyRightHand(int x, int y, GameFrame frame) {
        super(x, y, 100, 0, 0, 0, 0, false, frame);
        this.setHeight(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setWidth(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        this.setVisible(true);
        setSize(Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE);
        background = MyProjectData.getProjectData().getSmileyRightHand();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, Constant.NORMAL_BOSS_FIGHT_CHUNK_SIZE, null);

    }
}
