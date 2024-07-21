package Models;

import Controller.BossFight.BossFightManger;
import Controller.Enemy.EnemyController;
import Controller.Game.*;
import Controller.Menu.ShrinkageController;
import Models.BossFight.BossFightAttackParadigm;
import Models.Games.UpdateToPPanel;
import Models.Epsilon.Epsilon;
import Models.Games.CheckTheStateOfTheGame;
import Models.Games.SkillTreeLogic;
import MyProject.MyProjectData;
import View.Game.GameFrame;
import View.Game.InputListener;
import View.Menu.StorePanel.StorePanelGraphics;
import View.Menu.TopPanel;
import View.MusicPlayer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private boolean isRunning;

    protected Constant constant;
    protected ArrayList<GameFrame> gameFrames = new ArrayList<>();
    protected GameFrame epsilonFrame;
    public UpdateToPPanel updateToPPanel;
    public ShrinkageController shrinkageController;
    public StoreController storeController;
    protected Intersection intersection;
    protected Epsilon epsilon;
    protected InputListener inputListener;
    protected MusicPlayer musicPlayer;
    private StorePanelGraphics storePanel;
    private TopPanel topPanel;
    private EnemyController enemyController;
    private IntersectionController intersectionController;
    private CollectibleController collectibleController;
    private CheckTheStateOfTheGame checkTheStateOfTheGame;
    private CheckPointController checkPointController;
    private SkillTreeLogic skillTreeLogic;
    private BossFightManger bossFightManger;
    private BossFightAttackParadigm bossFightAttackParadigm;
    private ArchmireAoeController archmireAoeController;
    public Game(Constant constant){
        this.constant = constant;
        Constant.setIsRunning(true);
        epsilonFrame = new GameFrame(Constant.FIRST_HEIGHT, Constant.FIRST_WIDTH, false, false);
        epsilonFrame.addExitButtonToThisFrame();
        epsilon = new Epsilon(350, 350, epsilonFrame);
        musicPlayer = new MusicPlayer("Sounds/BackgroundMusic.wav", true);
        topPanel = new TopPanel();
        intersectionController = new IntersectionController();
        storePanel = new StorePanelGraphics(constant,epsilon);
        intersection = new Intersection();
        updateToPPanel = new UpdateToPPanel(this, constant);
        storeController = new StoreController(this);
        enemyController = new EnemyController(this);
        shrinkageController = new ShrinkageController(constant.getMinHeightForShrinkage(), constant.getMinWidthForShrinkage(), constant.getReduceForeShrinkage());
        collectibleController = new CollectibleController();
        checkTheStateOfTheGame = new CheckTheStateOfTheGame();
        checkPointController = new CheckPointController(epsilon);
        skillTreeLogic = new SkillTreeLogic(this);
        bossFightAttackParadigm = new BossFightAttackParadigm();
        bossFightManger = new BossFightManger(bossFightAttackParadigm);
        archmireAoeController = new ArchmireAoeController();
        makeEpsilonFrame(epsilonFrame);
        epsilonFrame.addToGamePanel(epsilon);
        getGameFrames().add(epsilonFrame);

    }
    public void makeEpsilonFrame(GameFrame gameFrame){
        inputListener = new InputListener(gameFrame, constant, epsilon);
        epsilonFrame.addJPanel(topPanel);
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

    public StorePanelGraphics getStorePanel() {
        return storePanel;
    }

    public void setStorePanel(StorePanelGraphics storePanel) {
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
        JOptionPane.showMessageDialog(new JFrame(),"Earned XP: " + constant.getPlayerXP(), "XP Earned", JOptionPane.INFORMATION_MESSAGE);
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

    public ArrayList<GameFrame> getGameFrames() {
        return gameFrames;
    }

    public void setGameFrames(ArrayList<GameFrame> gameFrames) {
        this.gameFrames = gameFrames;
    }

    public GameFrame getEpsilonFrame() {
        return epsilonFrame;
    }

    public void setEpsilonFrame(GameFrame epsilonFrame) {
        this.epsilonFrame = epsilonFrame;
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Epsilon epsilon) {
        this.epsilon = epsilon;
    }

    public IntersectionController getIntersectionController() {
        return intersectionController;
    }

    public void setIntersectionController(IntersectionController intersectionController) {
        this.intersectionController = intersectionController;
    }

    public CollectibleController getCollectibleController() {
        return collectibleController;
    }

    public void setCollectibleController(CollectibleController collectibleController) {
        this.collectibleController = collectibleController;
    }

    public CheckTheStateOfTheGame getCheckTheStateOfTheGame() {
        return checkTheStateOfTheGame;
    }

    public void setCheckTheStateOfTheGame(CheckTheStateOfTheGame checkTheStateOfTheGame) {
        this.checkTheStateOfTheGame = checkTheStateOfTheGame;
    }

    public CheckPointController getCheckPointController() {
        return checkPointController;
    }

    public void setCheckPointController(CheckPointController checkPointController) {
        this.checkPointController = checkPointController;
    }

    public SkillTreeLogic getSkillTreeLogic() {
        return skillTreeLogic;
    }

    public void setSkillTreeLogic(SkillTreeLogic skillTreeLogic) {
        this.skillTreeLogic = skillTreeLogic;
    }

    public BossFightManger getBossFightManger() {
        return bossFightManger;
    }

    public void setBossFightManger(BossFightManger bossFightManger) {
        this.bossFightManger = bossFightManger;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public BossFightAttackParadigm getBossFightAttackParadigm() {
        return bossFightAttackParadigm;
    }

    public void setBossFightAttackParadigm(BossFightAttackParadigm bossFightAttackParadigm) {
        this.bossFightAttackParadigm = bossFightAttackParadigm;
    }

    public ArchmireAoeController getArchmireAoeController() {
        return archmireAoeController;
    }

    public void setArchmireAoeController(ArchmireAoeController archmireAoeController) {
        this.archmireAoeController = archmireAoeController;
    }
}
