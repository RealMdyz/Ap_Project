package Models.Enemy;

import Models.Constant;
import MyProject.MyProjectData;

import java.awt.*;

public class Omenoct extends Enemy{

    public Omenoct(int x, int y) {
        super(x, y, 20, 8, 4, 8, 4, false);

        this.setHeight(Constant.getHeightOfOmenoct());
        this.setWidth(Constant.getWidthOfOmenoct());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        setSize(Constant.getWidthOfSquarantine(), Constant.getHeightOfOmenoct());
        background = MyProjectData.getProjectData().getOmenoct();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }

    @Override
    public void move(int xLimit, int yLimit) {
        // EndPoint =
        addX((- this.getX() + Constant.getEndXOmenoct()) / 10);
        addX((- this.getY() + Constant.getEndYOmenoct()) / 10);
    }
}
