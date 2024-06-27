package Models.Enemy;

import Models.Constant;
import Models.Epsilon.Shot;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class Wyrm extends Enemy{

    ArrayList<Shot> shots = new ArrayList<>();
    boolean clockWise = false;
    public Wyrm(int x, int y, GameFrame frame) {
        super(x, y, 12, 2, 8, 0, 8, false, frame);
        this.setHeight(Constant.WIDTH_OF_WYRM);
        this.setWidth(Constant.HEIGHT_OF_WYRM);
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        setSize(Constant.WIDTH_OF_WYRM, Constant.HEIGHT_OF_WYRM);
        background = MyProjectData.getProjectData().getWyrm();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }

    @Override
    public void move(int xLimit, int yLimit) {
        // EndPoint =
        addX(0);
        addX(0);
    }

    @Override
    public void specialPowers(int xEpsilon, int yEpsilon) {
        if(Math.random() <0.1){
            // add a shot !
        }
    }

    public boolean isClockWise() {
        return clockWise;
    }

    public void setClockWise(boolean clockWise) {
        this.clockWise = clockWise;
    }
}
