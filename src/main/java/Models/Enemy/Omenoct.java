package Models.Enemy;

import Models.Constant;
import Models.Epsilon.Shot;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Omenoct extends Enemy{

    public ArrayList<Shot> shots = new ArrayList<>();
    public long lastShotTime = 0;

    public Omenoct(int x, int y, GameFrame frame) {

        super(x, y, 20, 8, 4, 8, 4, false, frame);

        this.setHeight(Constant.getHeightOfOmenoct());
        this.setWidth(Constant.getWidthOfOmenoct());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        setSize(Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct());
        background = MyProjectData.getProjectData().getOmenoct();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }

    @Override
    public void move() {
        // EndPoint =
        addX(0);
        addX(0);
    }

    @Override
    public void specialPowers(int xEpsilon, int yEpsilon) {
        shotAShot(xEpsilon, yEpsilon);
        moveShots();
    }
    private void shotAShot(int xEpsilon, int yEpsilon){
        if(System.currentTimeMillis() - lastShotTime > Constant.getEveryShotOmenoct()){
            Shot shot = new Shot(this.getX(), this.getY(), this.currentFrame, false);
            shot.setV(xEpsilon, yEpsilon);
            shots.add(shot);
            currentFrame.addToGamePanel(shot);
            lastShotTime = System.currentTimeMillis();
        }
    }
    private void moveShots(){
        for(Shot shot : shots){
            shot.move();
        }
    }
}
