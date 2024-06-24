package Models.Enemy;

import Models.Constant;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Archmire extends Enemy{

    boolean mini;
    int aoePower, drownPower;
    long aoeTime;


    public Archmire(int x, int y, boolean isMini, GameFrame frame) {
        super(x, y, 30, isMini? 2 : 5, isMini ? 3 : 6, 0, 0, true, frame);
        aoeTime = 5000;
        aoePower = 2;
        drownPower = 10;
        this.setHeight(Constant.getHeightOfArchmire());
        this.setWidth(Constant.getWidthOfArchmire());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        this.setVisible(true);
        setSize(Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick());
        background = MyProjectData.getProjectData().getArchmire();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick(), null);
    }

    @Override
    public void move(int xLimit, int yLimit) {
        addX(0);
        addX(0);
    }
}
