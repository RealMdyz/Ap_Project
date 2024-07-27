package Models.Enemy.Barricados;

import Models.Constant;
import Models.Enemy.Enemy;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;

public class Barricados extends Enemy {

    private long spawnTime;
    private static int EXPIRATION_TIME = 120000;
    public Barricados(int x, int y, GameFrame frame) {
        super(0, 0, 99999, 0, 0, 4, 0, false, frame);
        this.setHeight(Constant.SIDE_LENGTH_OF_BARRICADOS);
        this.setWidth(Constant.SIDE_LENGTH_OF_BARRICADOS);
        this.setVisible(true);
        setSize(Constant.SIDE_LENGTH_OF_BARRICADOS, Constant.SIDE_LENGTH_OF_BARRICADOS);
        background = MyProjectData.getProjectData().getBarricados();
        spawnTime = System.currentTimeMillis();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.SIDE_LENGTH_OF_BARRICADOS, Constant.SIDE_LENGTH_OF_BARRICADOS, null);

    }
    public boolean isExpired(){
        if(System.currentTimeMillis() - spawnTime > EXPIRATION_TIME){
            return true;
        }
        return false;
    }
}
