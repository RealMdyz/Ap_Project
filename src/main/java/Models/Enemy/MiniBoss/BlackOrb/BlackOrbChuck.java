package Models.Enemy.MiniBoss.BlackOrb;

import Models.AttackType;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.EntityType;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackOrbChuck extends Enemy {
    private List<Line> linesToDraw = new ArrayList<>();
    public final int xPos, yPos;

    public BlackOrbChuck(GameFrame gameFrame) {
        super(10, 10, 30, 1, 30, 0, 12, true, gameFrame);
        this.setHeight(Constant.ORB_SIZE);
        this.setWidth(Constant.ORB_SIZE);
        this.setVisible(true);
        xPos = currentFrame.getX() + 10;
        yPos = currentFrame.getY() + 10;
        setSize(Constant.ORB_SIZE, Constant.ORB_SIZE);
        background = MyProjectData.getProjectData().getOrb();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, getWidth(), getHeight(), null);

    }
    public void drawLaserOnGameFrame() {
        Graphics g = currentFrame.getGraphics();
        if (g != null) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.RED);
            g2D.setStroke(new BasicStroke(3));
            synchronized (this){
                for (Line line : linesToDraw) {
                    g2D.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
                }
            }

            g.dispose();
        }
    }

    public void addLineToDraw(Point p1, Point p2) {
        linesToDraw.add(new Line(p1, p2));
        repaint(); // بازخوانی مجدد پنجره برای رسم خطوط جدید
    }

    public List<Line> getLinesToDraw() {
        return linesToDraw;
    }

    public void setLinesToDraw(List<Line> linesToDraw) {
        this.linesToDraw = linesToDraw;
    }

}
