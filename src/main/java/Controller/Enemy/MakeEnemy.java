package Controller.Enemy;

import Models.Constant;
import Models.Enemy.*;
import Models.Game;
import View.Game.GameFrame;

import javax.imageio.metadata.IIOMetadataController;
import java.util.Random;

public class MakeEnemy {
    private Game game;
    public MakeEnemy(Game game){
        this.game = game;
    }

    public Enemy makeRandomEnemy(int random, int currentWaveIndex){
        if(currentWaveIndex <= 2){
            random %= 4;
            if(random == 0){
                return makeNecropick();
            }
            else if(random == 1){
                return makeOmenoct();
            }
            else if(random == 2){
                return makeArchmire();
            }
            else {
                return makeWyrm();
            }
        }
        return null;
    }
    public Necropick makeNecropick(){
        Random random =new Random();
        int locX = random.nextInt() % 350 + 15;
        int locY = random.nextInt() % 350 + 15;
        return new Necropick(locX, locY, game.getEpsilonFrame());
    }
    public Omenoct makeOmenoct(){
        Random random =new Random();
        int locX = random.nextInt() % 450 + 50;
        int locY = random.nextInt() % 450 + 50;
        int l = random.nextInt() % 2;
        return new Omenoct(Math.max(locX * l, Constant.getWidthOfOmenoct()) , Math.max(locY * (1 - l), Constant.getHeightOfOmenoct()), game.getEpsilonFrame());
    }
    public Archmire makeArchmire(){
        Random random = new Random();
        int locX = random.nextInt() % 450 + 45;
        int locY = random.nextInt() % 450 + 45;

        if(random.nextInt() % 2 == 0){
            return new Archmire(locX, locY, false, game.getEpsilonFrame());
        }
        else {
            return new Archmire(locX, locY, true, game.getEpsilonFrame());
        }

    }
    private Wyrm makeWyrm(){
        Random random = new Random();
        int locX = random.nextInt() % 1000 + 45;
        int locY = random.nextInt() % 800 + 45;
        GameFrame gameFrame = new GameFrame(game.getConstant(), Constant.WIDTH_OF_WYRM, Constant.HEIGHT_OF_WYRM, true, false);
        game.getGameFrames().add(gameFrame);
        gameFrame.setVisible(true);
        game.getEpsilonFrame().requestFocus();
        gameFrame.setBounds(locX, locY, Constant.WIDTH_OF_WYRM, Constant.HEIGHT_OF_WYRM);

        return new Wyrm(15, 15, gameFrame);
    }
}
