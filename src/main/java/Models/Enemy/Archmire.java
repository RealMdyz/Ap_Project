package Models.Enemy;

import Models.Constant;
import MyProject.MyProjectData;

import java.awt.*;

public class Archmire extends Enemy{

    boolean mini;

    public Archmire(int x, int y, boolean isMini) {
        super(x, y, 30, isMini? 2 : 5, isMini ? 3 : 6, 0, 0, true);

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
}
