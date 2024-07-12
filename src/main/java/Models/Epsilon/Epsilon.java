package Models.Epsilon;

import Controller.Game.EpsilonController;
import Models.Constant;
import Models.Game;
import Models.Moveable;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Epsilon extends ObjectsInGame implements Moveable {
    public int powerOfShot = 5;
    private int radius = 35;
    protected ArrayList<Shot> shots = new ArrayList<>();
    EpsilonLogic epsilonLogic;
    EpsilonController epsilonController;


    public Epsilon(int x, int y, GameFrame jFrame) {
        super(x, y, 100, jFrame);
        epsilonLogic = new EpsilonLogic();
        epsilonController = new EpsilonController();
        this.setHeight(70);
        this.setWidth(70);
        setSize(this.getWidth(), this.getHeight());
        background = MyProjectData.getProjectData().getEpsilonCircle();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }
    public void specialPower(Game game){
        shotMove();
        epsilonLogic.fireShot(game);
    }
    private void shotMove(){
        for(Shot shot : shots)
            shot.move();
    }

    @Override
    public void move() {
        epsilonLogic.move(this);
    }

    public int getRadius() {
        return radius + 3;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }

    public int getPowerOfShot() {
        return powerOfShot;
    }

    public void setPowerOfShot(int powerOfShot) {
        this.powerOfShot = powerOfShot;
    }

    public EpsilonLogic getEpsilonLogic() {
        return epsilonLogic;
    }

    public void setEpsilonLogic(EpsilonLogic epsilonLogic) {
        this.epsilonLogic = epsilonLogic;
    }

    public EpsilonController getEpsilonController() {
        return epsilonController;
    }

    public void setEpsilonController(EpsilonController epsilonController) {
        this.epsilonController = epsilonController;
    }
}
