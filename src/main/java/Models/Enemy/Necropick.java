package Models.Enemy;

import Models.Constant;
import Models.Epsilon.Shot;
import Models.Game;
import MyProject.MyProjectData;

import java.awt.*;
import java.util.ArrayList;

public class Necropick extends Enemy{

    long time = System.currentTimeMillis();
    private ArrayList<Shot> shots = new ArrayList<>();

    int[] x = {-50, -50, 50, 50};
    int[] y = {50, -50, 50, -50};

    boolean canShot = true;
    public Necropick(int x, int y) {
        super(x, y, 10, 4, 2, 0, 5, false);

        this.setHeight(Constant.getHeightOfNecropick());
        this.setWidth(Constant.getWidthOfNecropick());
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        this.setVisible(false);
        setSize(Constant.getWidthOfNecropick(), Constant.getHeightOfNecropick());
        background = MyProjectData.getProjectData().getNecropick();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, Constant.getWidthOfOmenoct(), Constant.getHeightOfOmenoct(), null);
    }

    @Override
    public void move(int xLimit, int yLimit) {
        // EndPoint =
        addX(0);
        addX(0);
    }
    public void checkEsp(Game game){
        if(System.currentTimeMillis() - this.getTime() > 12000){
            this.setTime(System.currentTimeMillis());
        }
        else if(System.currentTimeMillis() - this.getTime() < 8000){
            this.setVisible(false);
            canShot = true;
        }
        else {
            this.setVisible(true);
            if(canShot){
                for(int i = 0; i < 4; i++){
                    Shot shot = new Shot(getX(), getY());
                    shot.setV(getX() + x[i],  getY() + y[i]);
                    shots.add(shot);
                    game.getGameFrame().getGamePanel().add(shot);
                }
                canShot = false;
            }
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }
}
