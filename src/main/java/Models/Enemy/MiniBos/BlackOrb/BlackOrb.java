package Models.Enemy.MiniBos.BlackOrb;

import Models.Constant;
import Models.Game;
import View.Game.GameFrame;

import java.awt.*;
import java.util.ArrayList;

public class BlackOrb {
    int xCenter, yCenter;
    int sideLength = 60;
    int spawnChuck = 0;
    long lastChuckSpawnTime = 0;
    public static final long SPAWN_TIME = 2000;
    ArrayList<BlackOrbChuck> blackOrbChucks = new ArrayList<>();
    public BlackOrb(int xCenter, int yCenter) {
        for (int i = 0; i < 5; i++) {
            double angle = 2 * Math.PI * i / 5 - Math.PI / 2;
            double x = xCenter + sideLength * Math.cos(angle);
            double y = yCenter + sideLength * Math.sin(angle);

            GameFrame gameFrame = new GameFrame(0, 0, true, false);
            gameFrame.setBounds((int) x, (int) y, Constant.SIDE_LENGTH_OF_BlACK_ORB, Constant.SIDE_LENGTH_OF_BlACK_ORB);

            BlackOrbChuck blackOrbChuck = new BlackOrbChuck(gameFrame);
            gameFrame.addToGamePanel(blackOrbChuck);
            gameFrame.repaint();
            blackOrbChucks.add(blackOrbChuck);
        }
        drawLasers();
    }

    public void specialPower() {

    }

    public void move() {
        for (BlackOrbChuck blackOrbChuck : blackOrbChucks) {
            blackOrbChuck.repaint();
        }
    }
    public void drawLasers() {
        for (int i = 0; i < blackOrbChucks.size(); i++) {
            for (int j = 0; j < blackOrbChucks.size(); j++) {
                if(i == j)
                    continue;
                BlackOrbChuck chuck1 = blackOrbChucks.get(i);
                BlackOrbChuck chuck2 = blackOrbChucks.get(j);
                Point p1 = new Point(
                        chuck1.getWidth() / 2,
                        chuck1.getHeight() / 2
                );
                Point p2 = new Point(
                        chuck2.getX() + chuck2.getWidth() / 2 + chuck2.getCurrentFrame().getX() - chuck1.getCurrentFrame().getX(),
                        chuck2.getY() + chuck2.getHeight() / 2 + chuck2.getCurrentFrame().getY() - chuck1.getCurrentFrame().getY()
                );
                chuck1.addLineToDraw(p1, p2);

            }
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
}
