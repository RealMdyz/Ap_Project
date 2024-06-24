package Controller.Enemy;

import Models.Enemy.Archmire;
import Models.Enemy.Enemy;
import Models.Enemy.Necropick;
import Models.Enemy.Omenoct;

import javax.imageio.metadata.IIOMetadataController;
import java.util.Random;

public class MakeEnemy {
    public MakeEnemy(){

    }

    public Enemy makeRandomEnemy(int random, int currentWaveIndex){
        if(currentWaveIndex <= 2){
            random %= 3;
            if(random == 0){
                return makeNecropick();
            }
            else if(random == 1){
                return makeOmenoct();
            }
            else {
                return makeArchmire();
            }
        }
        return null;
    }
    public Necropick makeNecropick(){
        return null;
    }
    public Omenoct makeOmenoct(){
        return null;
    }
    public Archmire makeArchmire(){
        return null;
    }
}
