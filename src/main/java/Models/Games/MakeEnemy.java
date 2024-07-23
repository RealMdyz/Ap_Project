package Models.Games;

import Controller.Game.FrameIntersection;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.Barricados.Barricados;
import Models.Enemy.Normal.*;
import Models.Game;
import View.Game.GameFrame;

import java.util.Random;

public class MakeEnemy {
    private Game game;
    public MakeEnemy(Game game){
        this.game = game;
    }

    public Enemy makeRandomEnemy(int random, int currentWaveIndex){
        if(currentWaveIndex <= 2){
            random %= 4;
            random = Math.abs(random);
            if(random == 0) {
                return makeNecropick();
            }
            else if(random == 1){
                return makeOmenoct();
            }
            else if(random == 2 ){
                return makeArchmire();
            }
            else{
                return makeWyrm();
            }

        }
        else{
            random %= 5;
            random = Math.abs(random);

            if(random == 0) {
                return makeNecropick();
            }
            else if(random == 1){
                return makeOmenoct();
            }
            else if(random == 2 ){
                return makeArchmire();
            }
            else if(random == 3){
                return makeWyrm();
            }
            else {
                return makebarricados();
            }
        }

    }
    public Necropick makeNecropick(){
        Random random =new Random();
        int locX = random.nextInt() % 350 + 15;
        int locY = random.nextInt() % 350 + 15;
        return new Necropick(locX, locY, game.getEpsilonFrame());
    }
    public Omenoct makeOmenoct(){
        Random random =new Random();
        int locX = Math.abs(random.nextInt() % 350 + 50);
        int locY = Math.abs(random.nextInt() % 450 + 50);
        int randomSide  = Math.abs(random.nextInt() % 4);
        return new Omenoct(locX , locY, randomSide, game.getEpsilonFrame());
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
        GameFrame gameFrame = new GameFrame(0, 0, true, false);
        game.getGameFrames().add(gameFrame);
        gameFrame.setVisible(true);
        game.getEpsilonFrame().requestFocus();
        gameFrame.setBounds(locX, locY, Constant.WIDTH_OF_WYRM, Constant.HEIGHT_OF_WYRM);

        return new Wyrm(15, 15, gameFrame);
    }
    private Barricados makebarricados() {
        Random random = new Random();
        int locX, locY;
        GameFrame gameFrame;

        // Find a valid spawn position
        do {
            if(Math.random() < 0.1){
                makeRandomEnemy(random.nextInt(), game.getEnemyController().getCurrentWaveIndex());
            }
            locX = Math.abs(random.nextInt() % 1000); // Adjust the range as necessary
            locY = Math.abs(random.nextInt() % 600); // Adjust the range as necessary
            gameFrame = new GameFrame(Constant.SIDE_LENGTH_OF_BARRICADOS, Constant.SIDE_LENGTH_OF_BARRICADOS, true, true);
        } while (!isValidSpawnPosition(gameFrame, locX, locY));

        gameFrame.setBounds(locX, locY, Constant.SIDE_LENGTH_OF_BARRICADOS, Constant.SIDE_LENGTH_OF_BARRICADOS);
        game.getGameFrames().add(gameFrame);
        gameFrame.setVisible(true);
        game.getEpsilonFrame().requestFocus();

        return new Barricados(gameFrame);
    }

    private boolean isValidSpawnPosition(GameFrame newFrame, int x, int y) {
        newFrame.setBounds(x, y, Constant.SIDE_LENGTH_OF_BARRICADOS, Constant.SIDE_LENGTH_OF_BARRICADOS);

        for (GameFrame existingFrame : game.getGameFrames()) {
            if (FrameIntersection.twoFrameIntersection(newFrame, existingFrame)) {
                return false; // Intersection found
            }
        }
        return true; // No intersection
    }

}

