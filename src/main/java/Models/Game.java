package Models;

import Controller.Intersection;
import Models.Epsilon.Epsilon;
import View.Game.GameFrame;
import View.Game.InputListener;
import View.Menu.StorePanel;

public class Game {
    private boolean isRunning;
    protected GameFrame gameFrame;
    protected Constant constant;
    protected Intersection intersection;
    protected Epsilon epsilon;
    protected InputListener inputListener;
    private StorePanel storePanel;


    public Game(){
        constant = new Constant();
        Constant.setIsRunning(true);
        epsilon = new Epsilon(500, 500);
        gameFrame = new GameFrame(constant, epsilon);
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
}
