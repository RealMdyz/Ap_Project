package Models.Games;

import Controller.Game.Intersection;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Vertex;
import Models.Game;
import Models.ObjectsInGame;

public class SkillTreeLogic {
    // هر چیزی مربوط بهش میشه رو اینجا میزنم و اینجا ران میکنم
    Game game;
    boolean writOfCerberusOpenBefore = false;

    public SkillTreeLogic(Game game){
        this.game = game;
    }
    public void runSkills(){
        if(Constant.isqPressed()){
            attackProcess();
            defendProcess();
            changeShapeProcess();
        }
    }
    public void attackProcess(){
        if(Constant.levelOfAttack >= 1)
            game.getEpsilon().setPowerOfShot(7);
        if(Constant.levelOfAttack >= 2){
            if(game.getEpsilon().getEpsilonLogic().isWritOfAstrapeAvailable()){
                if(checkTheIntersectionForAllEnemiesAndReduceTheHp(2, game.getEpsilon())){
                    game.getEpsilon().getEpsilonLogic().setLastWritOfAstrape(System.currentTimeMillis());
                }
            }

        }
        if(Constant.levelOfAttack >= 3){
            if(!writOfCerberusOpenBefore){
               addCerberus();
               writOfCerberusOpenBefore = true;
            }
            if(game.getEpsilon().getEpsilonLogic().isWritOfCerberusAvailable()){
                for(Vertex vertex : game.getEpsilon().getEpsilonLogic().getVerticesForCerberus()){
                    for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                        if(Intersection.isCompletelyInside(vertex, enemy)){
                            enemy.reduceHp(10);
                            game.getEpsilon().getEpsilonLogic().setLastWritOfCerberus(System.currentTimeMillis());
                        }
                    }
                }
            }
        }
    }
    public void defendProcess(){
        if(Constant.levelOfDefend >= 1){
            if(System.currentTimeMillis() - game.getEpsilon().getEpsilonLogic().getLastWritOfAceso() > 1000){
                game.getEpsilon().getEpsilonLogic().setLastWritOfAceso(System.currentTimeMillis());
                game.getEpsilon().reduceHp(-1);
            }
        }
        if(Constant.levelOfDefend >= 2){

        }
        if(Constant.levelOfDefend >= 3){

        }
    }
    public void changeShapeProcess(){
        if(Constant.levelOfChangeShape >= 1){

        }
        if(Constant.levelOfChangeShape >= 2){

        }
        if(Constant.levelOfChangeShape >= 3){

        }
    }
    private void addCerberus(){
        Epsilon epsilon = game.getEpsilon();
        Vertex vertex = new Vertex(epsilon.getX() + epsilon.getWidth(), epsilon.getY() + epsilon.getHeight(), 10, epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForCerberus().add(vertex);
        Vertex vertex1 = new Vertex(epsilon.getX(), epsilon.getY() + epsilon.getHeight(), 10, epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForCerberus().add(vertex1);
        Vertex vertex2 = new Vertex(epsilon.getCenterX() - 15, epsilon.getY() - 15, 10, epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForCerberus().add(vertex2);
        epsilon.getCurrentFrame().addToGamePanel(vertex);
        epsilon.getCurrentFrame().addToGamePanel(vertex1);
        epsilon.getCurrentFrame().addToGamePanel(vertex2);
    }
    private boolean checkTheIntersectionForAllEnemiesAndReduceTheHp(int rds , ObjectsInGame objectsInGame){
        boolean c = false;
        for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
            if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(enemy, objectsInGame)){
                enemy.reduceHp(rds);
                c = true;
            }
        }
        for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
            for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks()){
                if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(blackOrbChuck, objectsInGame)){
                    blackOrbChuck.reduceHp(rds);
                    c = true;
                }
            }
        }
        return c;
    }
}
