package Models.Enemy;

import Models.Aggression;
import Models.Constant;
import Models.LocalRouting;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Squarantine extends ObjectsInGame implements LocalRouting, Aggression {
    Constant constant;
    private int hp = 10;
    private int collectibleNumber = 1;
    private int xpForEachCollectible = 5;
    public Squarantine(int x, int y) {
        super(x, y);
        this.setHeight(Constant.getHeightOfSquarantine());
        this.setWidth(Constant.getWidthOfSquarantine());
        setSize(Constant.getWidthOfSquarantine(), Constant.getHeightOfSquarantine());
        background = MyProjectData.getProjectData().getSquarAntine();
    }

    @Override
    public void localRouting() {

    }
    @Override
    public void aggression() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfSquarantine(), Constant.getHeightOfSquarantine(), null);
        /*
        g2D.fill(new Arc2D.Double(getX() - radius, getY() - radius, 2 * radius, 2 * radius, 0, 360, Arc2D.PIE));
        g2D.dispose();
        */
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCollectibleNumber() {
        return collectibleNumber;
    }

    public void setCollectibleNumber(int collectibleNumber) {
        this.collectibleNumber = collectibleNumber;
    }

    public int getXpForEachCollectible() {
        return xpForEachCollectible;
    }

    public void setXpForEachCollectible(int xpForEachCollectible) {
        this.xpForEachCollectible = xpForEachCollectible;
    }
}
