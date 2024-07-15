package Controller.Game;


import Models.Enemy.Enemy;
import Models.Epsilon.Collectible;
import View.Game.GameFrame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CollectibleController {

    protected ArrayList<Collectible> collectibles = new ArrayList<>();


    public CollectibleController(){

    }
    public void intersectionHappen(Collectible collectible){
        // in this were we should remove this collectble from the gamePanel collectibles and then add this;
        collectible.getCurrentFrame().removeFromGamePanel(collectible);
    }
    public void addingCollectiblesAfterDie(Enemy enemy){
        int numCollectibles = enemy.getCollectibleNumber();
        int radius = Math.max(enemy.getWidth(), enemy.getHeight());

        Random rand = new Random();
        GameFrame frame = enemy.getCurrentFrame();

        for (int i = 0; i < numCollectibles; i++) {
            double angle = 2 * Math.PI * rand.nextDouble();
            int distance = rand.nextInt(radius);

            int collectibleX = enemy.getX() + (int) (distance * Math.cos(angle));
            int collectibleY = enemy.getY() + (int) (distance * Math.sin(angle));

            Collectible collectible = new Collectible(collectibleX, collectibleY, 1, enemy.getXpForEachCollectible(), System.currentTimeMillis(), frame);
            collectibles.add(collectible);
            frame.addToGamePanel(collectible);
        }
    }
    public void checkTheExpirationTime(){
        ArrayList<Collectible> collectibleArrayList = new ArrayList<>();
        for(Collectible collectible : collectibles){
            if(collectible.isExpiration()){
                collectible.getCurrentFrame().removeFromGamePanel(collectible);
                collectibleArrayList.add(collectible);
            }
        }
        collectibles.removeAll(collectibleArrayList);
    }
    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
    }
}
