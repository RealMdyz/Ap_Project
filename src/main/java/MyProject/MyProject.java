package MyProject;

import Controller.GameLoop;
import Models.Game;
import View.Game.GameFrame;
import View.Menu.LoginPageShare;

public class MyProject {
    protected Game game;
    protected GameLoop gameLoop;
    public MyProject(){
        game = new Game();
        gameLoop = new GameLoop(game);
        new LoginPageShare(gameLoop, game);

    }
}
