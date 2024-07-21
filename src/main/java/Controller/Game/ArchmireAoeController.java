package Controller.Game;

import Models.Enemy.Enemy;
import Models.Enemy.Normal.Aoe;
import Models.Enemy.Normal.Archmire;
import Models.Game;

import java.util.ArrayList;

public class ArchmireAoeController {
    public ArchmireAoeController(){

    }
    public void control(Game game){
        updateAoE(game.getEnemyController().getEnemyArrayList());
        checkTheAoeDamage(game);
        if(Math.random() < 0.08)
             fadingTheAoeAre(game.getEnemyController().getEnemyArrayList());
    }
    public void updateAoE(ArrayList<Enemy> enemyArrayList) {
        for(Enemy enemy : enemyArrayList){
            if(enemy instanceof Archmire){
                ArrayList<Aoe> aoeArrayList = new ArrayList<>();
                Archmire archmire = (Archmire) enemy;
                for(Aoe aoe : archmire.getAoeList()){
                    if(aoe.isExpired()){
                        aoe.getCurrentFrame().removeFromGamePanel(aoe);
                        aoeArrayList.add(aoe);
                    }
                }
                archmire.getAoeList().removeAll(aoeArrayList);
                long current = System.currentTimeMillis();
                if(current - archmire.getLastAoeTime() > Archmire.TIME_AOE_LOOP){
                    archmire.addAoE(archmire.getX(), archmire.getY());
                    archmire.setLastAoeTime(current);
                }
            }
        }
    }
    public void checkTheAoeDamage(Game game){
        for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
            if(enemy instanceof Archmire){
                Archmire archmire = (Archmire) enemy;
                for(Enemy enemy1 : game.getEnemyController().getEnemyArrayList()){
                    if(!enemy1.equals(enemy))
                        archmire.checkAoEDamage(enemy1);
                }
                archmire.checkAoEDamage(game.getEpsilon());
            }
        }
    }
    public void fadingTheAoeAre(ArrayList<Enemy> enemyArrayList) {
        for (Enemy enemy : enemyArrayList) {
            if (enemy instanceof Archmire) {
                Archmire archmire = (Archmire) enemy;
                for (Aoe aoe : archmire.getAoeList()) {
                    aoe.fade();
                }
            }
        }
    }

}
