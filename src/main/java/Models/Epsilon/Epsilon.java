package Models.Epsilon;

import Controller.Game.EpsilonController;
import Models.*;
import MyProject.MyProjectData;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;


public class Epsilon extends ObjectsInGame implements Moveable {
    public int powerOfShot = 5;
    private int radius = 35;
    protected ArrayList<Shot> shots = new ArrayList<>();
    EpsilonLogic epsilonLogic;
    EpsilonController epsilonController;
    public static int successfulShots = 0;

    public Epsilon(int x, int y, GameFrame jFrame) {
        super(x, y, 100, jFrame);
        epsilonLogic = new EpsilonLogic();
        epsilonController = new EpsilonController(epsilonLogic);
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

    @Override
    public boolean reduceHp(int powerOfAttack, AttackType attackType, EntityType from) {
        if(attackType.equals(AttackType.MELEE)){
            if(Constant.isqPressed() && Constant.levelOfDefend <= 2 || Math.random() < 0.05){
                // reduce nothing !
            }
            else{
                this.setHp(this.getHp() - powerOfAttack);
            }
        }
        else{
            this.setHp(this.getHp() - powerOfAttack);
        }
        if(this.getHp() <= 0){
            return true;
        }
        return false;
    }
    public void reduceSizeBy10Percent() {
        this.setWidth((int) (this.getWidth() * 0.9));
        this.setHeight((int) (this.getHeight() * 0.9));
        setSize(this.getWidth(), this.getHeight());
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

    public void reduceFrameSize(int wallToHit) {
        // wallToHit: 0 = top, 1 = right, 2 = bottom, 3 = left
        int height = this.getCurrentFrame().getHeight();
        int width = this.getCurrentFrame().getWidth();

        switch (wallToHit) {
            case 0: // Top
                height *= 0.9;
                break;
            case 1: // Right
                width *= 0.9;
                // به جای جابجایی، فقط سایز را کم می‌کنیم
                break;
            case 2: // Bottom
                height *= 0.9;
                break;
            case 3: // Left
                width *= 0.9;
                // به جای جابجایی، فقط سایز را کم می‌کنیم
                break;
        }

        // محاسبه موقعیت جدید برای ایجاد اثر ضربه از سمت مخالف
        int newX = getCurrentFrame().getX();
        if (wallToHit == 2) { // Right
            newX -= (width * 0.05); // به سمت چپ جابجا می‌شود
        } else if (wallToHit == 0) { // Left
            newX += (width * 0.05); // به سمت راست جابجا می‌شود
        }

        int newY = getCurrentFrame().getY();
        if (wallToHit == 1) { // Right
            newY -= (height * 0.05); // به سمت چپ جابجا می‌شود
        } else if (wallToHit == 3) { // Left
            newX += (height * 0.05); // به سمت راست جابجا می‌شود
        }

        getCurrentFrame().setSize(width, height);
        getCurrentFrame().setLocation(newX, newY);
    }


}
