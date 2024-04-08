package MyProject;

import Controller.GameLoop;
import Models.Constant;
import Models.Game;
import View.Game.GameFrame;
import View.Menu.LoginPageShare;

public class MyProject {
    protected Game game;
    protected GameLoop gameLoop;
    public MyProject(){
        Constant constant = new Constant();
        game = new Game(constant);
        gameLoop = new GameLoop(game, constant);
        new LoginPageShare(gameLoop, game);

    }
}
