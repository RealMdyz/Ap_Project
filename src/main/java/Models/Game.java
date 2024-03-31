package Models;

public class Game {
    public boolean isRunning;

    public Game(){
        isRunning = true;
    }
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
