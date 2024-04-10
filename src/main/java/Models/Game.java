package Models;

import Controller.Intersection;
import Models.Epsilon.Epsilon;
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
    protected Intersection intersection;
    protected Epsilon epsilon;
    protected InputListener inputListener;
    protected MusicPlayer musicPlayer;
    private StorePanel storePanel;
    private TopPanel topPanel;


    public Game(Constant constant){
        this.constant = constant;
        Constant.setIsRunning(true);
        epsilon = new Epsilon(350, 350);
        MusicPlayer musicPlayer = new MusicPlayer("Sounds/BackgroundMusic.wav", true);
        topPanel = new TopPanel();
        gameFrame = new GameFrame(constant, epsilon, topPanel);
        storePanel = new StorePanel(constant);
        inputListener = new InputListener(gameFrame, constant);
        intersection = new Intersection();

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

    public void endGame(){
        JOptionPane.showMessageDialog(getGameFrame(), "Congratulations! You have defeated all waves.");
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
        Constant.setSavedXp(savedXp + constant.getPlayerXP());
        constant.writeInFile(Constant.getSavedXp(), levelOfWritOfAres, levelOfWritOfProteus, lForAceso);
        System.exit(0);

    }
}
