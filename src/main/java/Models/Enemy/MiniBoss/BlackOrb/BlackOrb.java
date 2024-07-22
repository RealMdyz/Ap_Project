package Models.Enemy.MiniBoss.BlackOrb;

import Models.Constant;
import Models.Game;
import Models.ObjectsInGame;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class BlackOrb {
    int xCenter, yCenter;
    int sideLength = 60;
    int spawnChuck = 0;
    long lastChuckSpawnTime = 0;
    public static final long SPAWN_TIME = 2000;
    public static final int LINE_POWER_OF_BLACK_ORB = 12;
    ArrayList<BlackOrbChuck> blackOrbChucks = new ArrayList<>();
    public long[][] lastLaserAttack = new long[5][5];
    public BlackOrb(int xCenter, int yCenter) {
        for (int i = 0; i < 5; i++) {
            double angle = 2 * Math.PI * i / 5 - Math.PI / 2;
            double x = xCenter + sideLength * Math.cos(angle);
            double y = yCenter + sideLength * Math.sin(angle);

            GameFrame gameFrame = new GameFrame(0, 0, true, false);
            gameFrame.setBounds((int) x, (int) y, Constant.SIDE_LENGTH_OF_BlACK_ORB, Constant.SIDE_LENGTH_OF_BlACK_ORB);

            BlackOrbChuck blackOrbChuck = new BlackOrbChuck(gameFrame);
            gameFrame.addToGamePanel(blackOrbChuck);
            blackOrbChuck.setVisible(false);
            gameFrame.repaint();
            blackOrbChucks.add(blackOrbChuck);
        }

    }

    public void specialPower() {

    }
    public void reDrawLasers(){
        for(int i = 0; i < blackOrbChucks.size(); i++){
            BlackOrbChuck chuck = blackOrbChucks.get(i);
            chuck.setLinesToDraw(new ArrayList<>());
        }
        drawLasers();
    }
    public void rePoint(){
        for(int i = 0; i < blackOrbChucks.size(); i++){

            BlackOrbChuck chuck = blackOrbChucks.get(i);
            chuck.setX(chuck.xPos - chuck.getCurrentFrame().getX());
            chuck.setY(chuck.yPos - chuck.getCurrentFrame().getY());

        }
    }


    public void move() {

    }
    public void drawLasers() {
        if(spawnChuck < 5)
                return;
        for (int i = 0; i < blackOrbChucks.size(); i++) {
            for (int j = 0; j < blackOrbChucks.size(); j++) {
                if(i == j)
                    continue;
                BlackOrbChuck chuck1 = blackOrbChucks.get(i);
                BlackOrbChuck chuck2 = blackOrbChucks.get(j);
                Point p1 = new Point(
                        chuck1.getWidth() / 2 + chuck1.getX(),
                        chuck1.getHeight() / 2 + chuck1.getY()
                );
                //p1.setLocation(0, 0);
                Point p2 = new Point(
                        chuck2.getX() + chuck2.getWidth() / 2 + chuck2.getCurrentFrame().getX() - chuck1.getCurrentFrame().getX(),
                        chuck2.getY() + chuck2.getHeight() / 2 + chuck2.getCurrentFrame().getY() - chuck1.getCurrentFrame().getY()
                );

                chuck1.addLineToDraw(p1, p2);
                lastLaserAttack[i][j] = 0;
            }
        }

        for (BlackOrbChuck blackOrbChuck : blackOrbChucks) {
            blackOrbChuck.drawLaserOnGameFrame();
            blackOrbChuck.repaint();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        drawLasers();
    }

    public void spawnAChuck(Game game) {
        BlackOrbChuck blackOrbChuck = blackOrbChucks.get(spawnChuck);
        game.getGameFrames().add(blackOrbChuck.getCurrentFrame());
        blackOrbChuck.getCurrentFrame().addToGamePanel(blackOrbChuck);
        blackOrbChuck.getCurrentFrame().setVisible(true);
        game.getEpsilonFrame().requestFocus();
        spawnChuck += 1;
        if(spawnChuck == 5){
            for(BlackOrbChuck blackOrbChuck1 : blackOrbChucks)
                blackOrbChuck1.setVisible(true);
        }
    }

    public long getLastChuckSpawnTime() {
        return lastChuckSpawnTime;
    }

    public void setLastChuckSpawnTime(long lastChuckSpawnTime) {
        this.lastChuckSpawnTime = lastChuckSpawnTime;
    }

    public int getSpawnChuck() {
        return spawnChuck;
    }

    public void setSpawnChuck(int spawnChuck) {
        this.spawnChuck = spawnChuck;
    }

    public ArrayList<BlackOrbChuck> getBlackOrbChucks() {
        return blackOrbChucks;
    }

    public void setBlackOrbChucks(ArrayList<BlackOrbChuck> blackOrbChucks) {
        this.blackOrbChucks = blackOrbChucks;
    }
    public boolean betweenTwoOfMyChunks(ObjectsInGame objectsInGame) {

        for (int i = 0; i < blackOrbChucks.size(); i++) {
            for (int j = 0; j < blackOrbChucks.size(); j++) {
                if (i != j) {
                    BlackOrbChuck chuck1 = blackOrbChucks.get(i);
                    BlackOrbChuck chuck2 = blackOrbChucks.get(j);

                    int x1 = chuck1.getCurrentFrame().getX() + chuck1.getX() + chuck1.getWidth() / 2;
                    int y1 = chuck1.getCurrentFrame().getY() + chuck1.getY() + chuck1.getHeight() / 2;
                    int x2 = chuck2.getCurrentFrame().getX() + chuck2.getX() + chuck2.getWidth() / 2;
                    int y2 = chuck2.getCurrentFrame().getY() + chuck2.getY() + chuck2.getHeight() / 2;

                    if (System.currentTimeMillis() - lastLaserAttack[i][j] > 1000 && isPointBetweenTwoPoints(objectsInGame.getCenterX() + objectsInGame.getCurrentFrame().getX(), objectsInGame.getCenterY() + objectsInGame.getCurrentFrame().getY(), x1, y1, x2, y2)) {
                        lastLaserAttack[i][j] = System.currentTimeMillis();
                        lastLaserAttack[j][i] = System.currentTimeMillis();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isPointBetweenTwoPoints(int px, int py, int x1, int y1, int x2, int y2) {
        double distance1 = Math.sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
        double distance2 = Math.sqrt((px - x2) * (px - x2) + (py - y2) * (py - y2));
        double lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double tolerance = 25; // Tolérance en pixels
       // System.out.println(px + " " + py + " " + x1 + " " + y1 + " " + x2 + " " + y2);
        // بررسی مجموع فاصله‌ها
        if (Math.abs(distance1 + distance2 - lineLength) <= tolerance) {
            // محاسبه فاصله عمود از خط
            double area = Math.abs((x2 - x1) * (py - y1) - (px - x1) * (y2 - y1));
            double lineDistance = area / lineLength;

            return lineDistance <= tolerance;
        }
        return false;
    }

    public int getxCenter() {
        return xCenter;
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return yCenter;
    }

    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }
}
