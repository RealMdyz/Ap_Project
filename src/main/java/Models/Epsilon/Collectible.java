package Models.Epsilon;

import Models.Constant;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Collectible extends ObjectsInGame {

    private int increaseXp;
    private long start = 0;
    public Collectible(int x, int y, int hp, int increaseXp, long start, GameFrame frame) {
        super(x, y, hp, frame);
        this.increaseXp = increaseXp;
        this.start = start;
        this.setHeight(20);
        this.setWidth(20);
        setSize(20, 20);
        background = MyProjectData.getProjectData().getCollectible();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, 20, 20, null);
    }

    public int getIncreaseXp() {
        return increaseXp;
    }

    public void setIncreaseXp(int increaseXp) {
        this.increaseXp = increaseXp;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
    public boolean isExpiration(){
        return System.currentTimeMillis() - start >= Constant.TIME_FOR_EACH_COLLECTIBLE;
    }
}
