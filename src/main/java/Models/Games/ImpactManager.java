package Models.Games;

import Models.Constant;
import Models.Epsilon.Collectible;
import Models.Epsilon.Epsilon;
import Models.ObjectsInGame;

public class ImpactManager {
    public ImpactManager(){

    }
    public static void checkAndDoingTheImpact(ObjectsInGame objectsInGame, int x, int y){
        if(objectsInGame instanceof Epsilon){
            objectsInGame.doImpact(x, y, Constant.RADIUS_OF_IMPACT);
        }
    }
}
