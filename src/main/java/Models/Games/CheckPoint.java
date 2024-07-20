package Models.Games;

import Models.Constant;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;

public class CheckPoint extends ObjectsInGame {

    private long startOfMe = 0;

    public CheckPoint(int x, int y, GameFrame currentFrame) {
        super(x, y, 0, currentFrame);
        this.setHeight(20);
        this.setWidth(20);
        setSize(this.getWidth(), this.getHeight());
        background = MyProjectData.getProjectData().getSquarAntine();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public long getStartOfMe() {
        return startOfMe;
    }

    public void setStartOfMe(long startOfMe) {
        this.startOfMe = startOfMe;
    }
    public boolean isExpire(){
        if(System.currentTimeMillis() - startOfMe > 10000 && startOfMe != 0)
            return true;
        return false;
    }
}
