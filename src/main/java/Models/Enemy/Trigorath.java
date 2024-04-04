package Models.Enemy;

import Models.Aggression;
import Models.Constant;
import Models.LocalRouting;
import Models.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;

public class Trigorath extends ObjectsInGame implements LocalRouting, Aggression {
    Constant constant;
    private int hp = 15;
    private int collectibleNumber = 2;
    private int xpForEachCollectible = 5;

    public Trigorath(int x, int y) {
        super(x, y);
        this.setHeight(Constant.getHeightOfTrighrath());
        this.setWidth(Constant.getWidthOfTrighrath());
        setSize(Constant.getWidthOfTrighrath(), Constant.getHeightOfTrighrath());
        background = MyProjectData.getProjectData().getTriGorath();
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
        g2D.drawImage(background, 0, 0, Constant.getWidthOfTrighrath(), Constant.getHeightOfTrighrath(), null);
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
