package Models;

import Controller.Enemy.EnemyController;
import Controller.Game.EpsilonShotController;
import Controller.Game.Intersection;
import Controller.Game.StoreController;
import Controller.Menu.ShrinkageController;
import Controller.Menu.UpdateToPPanel;
import Models.Epsilon.Epsilon;
import MyProject.MyProjectData;
import View.Game.GameFrame;
import View.Game.InputListener;
import View.Menu.StorePanel;
import View.Menu.TopPanel;
import View.MusicPlayer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private boolean isRunning;

    protected GameFrame gameFrame;
    protected Constant constant;

    public UpdateToPPanel updateToPPanel;
    public ShrinkageController shrinkageController;
    public EpsilonShotController epsilonShotController;
    public StoreController storeController;
    protected Intersection intersection;
    protected Epsilon epsilon;
    protected InputListener inputListener;
    protected MusicPlayer musicPlayer;
    private StorePanel storePanel;
    private TopPanel topPanel;
    private EnemyController enemyController;



    public Game(Constant constant){
        this.constant = constant;
        Constant.setIsRunning(true);
        epsilon = new Epsilon(350, 350);
        musicPlayer = new MusicPlayer("Sounds/BackgroundMusic.wav", true);
        topPanel = new TopPanel();
        gameFrame = new GameFrame(constant, epsilon, topPanel);
        storePanel = new StorePanel(constant,epsilon);
        inputListener = new InputListener(gameFrame, constant);
        intersection = new Intersection();
        updateToPPanel = new UpdateToPPanel(this, constant);
        epsilonShotController = new EpsilonShotController(this);
        storeController = new StoreController(this);
        enemyController = new EnemyController(this);
        shrinkageController = new ShrinkageController(constant.getMinHeightForShrinkage(), constant.getMinWidthForShrinkage(), constant.getReduceForeShrinkage());

    }


    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
    }

    public InputListener getInputListener() {
        return inputListener;
    }

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public StorePanel getStorePanel() {
        return storePanel;
    }

    public void setStorePanel(StorePanel storePanel) {
        this.storePanel = storePanel;
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public UpdateToPPanel getUpdateToPanel() {
        return updateToPPanel;
    }

    public void setUpdateToPanel(UpdateToPPanel updateToPPanel) {
        this.updateToPPanel = updateToPPanel;
    }

    public EpsilonShotController getEpsilonShotController() {
        return epsilonShotController;
    }

    public void setEpsilonShotController(EpsilonShotController epsilonShotController) {
        this.epsilonShotController = epsilonShotController;
    }

    public StoreController getStoreController() {
        return storeController;
    }

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
    }

    public UpdateToPPanel getUpdateToPPanel() {
        return updateToPPanel;
    }

    public void setUpdateToPPanel(UpdateToPPanel updateToPPanel) {
        this.updateToPPanel = updateToPPanel;
    }

    public ShrinkageController getShrinkageController() {
        return shrinkageController;
    }

    public void setShrinkageController(ShrinkageController shrinkageController) {
        this.shrinkageController = shrinkageController;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(EnemyController enemyController) {
        this.enemyController = enemyController;
    }

    public void endGame(){
        JOptionPane.showMessageDialog(gameFrame, "Earned XP: " + constant.getPlayerXP(), "XP Earned", JOptionPane.INFORMATION_MESSAGE);
        File file = new File("gameData");
        int savedXp;
        int levelOfWritOfAres;
        int levelOfWritOfProteus;
        int lForAceso;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // FirstLine: SavedXp;
            // SecondLine : LevelWritOfAres;
            // ThirdLine : LevelOfWritOfProteus;
            // FourthLine : writeOfAceso
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            savedXp = scanner.nextInt();
            levelOfWritOfAres = scanner.nextInt();
            levelOfWritOfProteus = (scanner.nextInt());
            lForAceso = scanner.nextInt();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(Constant.getPlayerXP());
//        Constant.setSavedXp(savedXp + Constant.getPlayerXP());
        constant.writeInFile(savedXp +  Constant.getPlayerXP(), levelOfWritOfAres, lForAceso, levelOfWritOfProteus);
        Constant.setSavedXp(savedXp +  Constant.getPlayerXP());
        // TODO;
        Constant.setPlayerXP(0);
        Constant.setqPressed(false);
        MusicPlayer.playOnce(MyProjectData.getProjectData().getEndOfGameSound());
    }

}
