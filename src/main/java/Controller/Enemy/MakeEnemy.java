package Controller.Enemy;

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
            random %= 2;
            if(random == 0){
                return makeNecropick();
            }
            else{
                return makeOmenoct();
            }
        }

        return null;
    }
    public Necropick makeNecropick(){
        Random random = new Random();
        return new Necropick(random.nextInt() % 450 + 20, random.nextInt() % 450 + 20);
    }
    public Omenoct makeOmenoct(){
        Random random = new Random();
        return new Omenoct(random.nextInt() % 450 + 20, random.nextInt() % 450 + 20);
    }
}
