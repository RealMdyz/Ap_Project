package Models;

import Models.Epsilon.Epsilon;
import View.Game.GameFrame;
import View.Game.InputListener;

public class Game {
    private boolean isRunning;
    protected GameFrame gameFrame;
    protected Constant constant;
    protected Epsilon epsilon;
    protected InputListener inputListener;

    public Game(){
        isRunning = true;
        constant = new Constant();
        epsilon = new Epsilon(500, 500);
        gameFrame = new GameFrame(constant, epsilon);
        inputListener = new InputListener(gameFrame, constant);

    }
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
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
}
