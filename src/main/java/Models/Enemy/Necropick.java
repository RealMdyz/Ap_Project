package Models.Enemy;

import Models.Constant;
import MyProject.MyProjectData;

import java.awt.*;

public class Necropick extends Enemy{

    long time = System.currentTimeMillis();

    public Necropick(int x, int y) {
        super(x, y, 10, 4, 2, 0, 5, false);

        this.setHeight(Constant.getHeightOfNecropick());
        this.setWidth(Constant.getWidthOfNecropick());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        this.setVisible(false);
        setSize(Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick());
        background = MyProjectData.getProjectData().getNecropick();
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
