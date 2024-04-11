package MyProject;

import Controller.GameLoop;
import Models.Constant;
import Models.Game;
import View.Game.GameFrame;
import View.Menu.LoginPageShare;

public class MyProject {

    public MyProject(){
        Constant constant = new Constant();
        new LoginPageShare(constant);

    }
}
