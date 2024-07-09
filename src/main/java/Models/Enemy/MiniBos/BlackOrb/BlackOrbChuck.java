package Models.Enemy.MiniBos.BlackOrb;

import Models.Constant;
import Models.Enemy.Enemy;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackOrbChuck extends Enemy {
    private List<Line> linesToDraw = new ArrayList<>();

    public BlackOrbChuck(GameFrame gameFrame) {
        super(10, 10, 30, 1, 30, 0, 12, true, gameFrame);
        this.setHeight(Constant.ORB_SIZE);
        this.setWidth(Constant.ORB_SIZE);
        this.setVisible(true);
        setSize(Constant.ORB_SIZE, Constant.ORB_SIZE);
        background = MyProjectData.getProjectData().getOrb();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        g2D.setColor(Color.RED); // رنگ خط
        g2D.setStroke(new BasicStroke(3)); // ضخامت خط

        for (Line line : linesToDraw) {
            g2D.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
        }
    }

    public void addLineToDraw(Point p1, Point p2) {
        linesToDraw.add(new Line(p1, p2));
        repaint(); // بازخوانی مجدد پنجره برای رسم خطوط جدید
    }

    private static class Line {
        Point p1, p2;

        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}
