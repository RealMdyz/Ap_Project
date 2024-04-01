package Models;

import View.Game.GameFrame;
import View.Game.InputListener;

public class Game {
    private boolean isRunning;
    protected GameFrame gameFrame;
    protected Constant constant;

    public Game(){
        isRunning = true;
        gameFrame = new GameFrame();
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
}
