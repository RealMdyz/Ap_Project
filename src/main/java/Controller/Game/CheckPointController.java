package Controller.Game;

import Models.Constant;
import Models.Epsilon.Epsilon;
import Models.Game;
import Models.Games.CheckPoint;
import View.Game.GameFrame;
import View.Menu.CheckPointPanel;
import View.Menu.SkillTreePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class CheckPointController {

    CheckPoint wave2CheckPoint; // this is appear at the start of the wave2 and this save the data of the wave Number 1
    CheckPoint wave4CheckPoint;// this is appear at the start of the wave4 and this save the data of the wave Number 1 , 2 , 3
    CheckPointPanel checkPointPanel;
    public CheckPointController(Epsilon epsilon){
        checkPointPanel = new CheckPointPanel(epsilon);
    }
    public void checkPointStart(Game game){
        if(game.getEnemyController().getCurrentWaveIndex() == 1){
            game.getEpsilon().getCurrentFrame().add(game.getCheckPointController().checkPointPanel);
            game.getCheckPointController().checkPointPanel.updateXpLabel(calculatePR(game));
            game.getCheckPointController().checkPointPanel.setVisible(true);
            game.getCheckPointController().wave2CheckPoint.getCurrentFrame().removeFromGamePanel(game.getCheckPointController().wave2CheckPoint);
            game.getCheckPointController().wave2CheckPoint.repaint();
            game.getCheckPointController().wave2CheckPoint = null;
            Constant.setOpenCheckPointPanel(true);
        }
        else if(game.getEnemyController().getCurrentWaveIndex() == 3){
            game.getEpsilon().getCurrentFrame().add(game.getCheckPointController().checkPointPanel);
            game.getCheckPointController().checkPointPanel.updateXpLabel(calculatePR(game));
            game.getCheckPointController().checkPointPanel.setVisible(true);
            game.getCheckPointController().wave4CheckPoint.getCurrentFrame().removeFromGamePanel(game.getCheckPointController().wave4CheckPoint);
            game.getCheckPointController().wave4CheckPoint.repaint();
            game.getCheckPointController().wave4CheckPoint = null;
            Constant.setOpenCheckPointPanel(true);
        }
    }
    private long calculatePR(Game game){
        int waveNumber =  game.getEnemyController().getCurrentWaveIndex();
        long progressRate = (waveNumber) * (game.getEnemyController().getWaveController().getEndOfEachWave()[waveNumber - 1] - game.getEnemyController().getWaveController().getStartOfEachWave()[waveNumber - 1]);
        long PR = (progressRate * Constant.getPlayerXP() * 10) / (game.getEpsilon().getHp());
        PR /= 1000;
        return PR;
    }
    public void control(int waveIndex, Epsilon epsilon){
        //System.out.println(waveIndex + " " + epsilon);
        if(waveIndex == 1){
            int x0 = epsilon.getX();
            int y0 = epsilon.getY();

            int distance = 100;

            Random rand = new Random();

            double angle = 2 * Math.PI * rand.nextDouble();

            int x = (int) (x0 + distance * Math.cos(angle));
            int y = (int) (y0 + distance * Math.sin(angle));

            wave2CheckPoint = new CheckPoint(x, y, epsilon.getCurrentFrame());
            epsilon.getCurrentFrame().addToGamePanel(wave2CheckPoint);
        }
        else if(waveIndex == 3){
            int x0 = epsilon.getX();
            int y0 = epsilon.getY();

            int distance = 100;

            Random rand = new Random();

            double angle = 2 * Math.PI * rand.nextDouble();

            int x = (int) (x0 + distance * Math.cos(angle));
            int y = (int) (y0 + distance * Math.sin(angle));

            wave4CheckPoint = new CheckPoint(x, y, epsilon.getCurrentFrame());
            epsilon.getCurrentFrame().addToGamePanel(wave4CheckPoint);
        }
    }



}
