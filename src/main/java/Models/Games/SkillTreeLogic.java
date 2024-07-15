package Models.Games;

import Controller.Game.Intersection;
import Models.*;
import Models.Enemy.Enemy;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrb;
import Models.Enemy.MiniBoss.BlackOrb.BlackOrbChuck;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Vertex;

import java.util.Random;

public class SkillTreeLogic {
    // هر چیزی مربوط بهش میشه رو اینجا میزنم و اینجا ران میکنم
    Game game;
    boolean writOfCerberusOpenBefore = false;
    boolean writOfProteusOpenBefore = false;
    boolean writOfEmpusaOpenBefore = false;
    boolean writOfDolusOpenBefore = false;

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
                boolean c = false;
                for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                    if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(enemy, game.getEpsilon())){
                        enemy.reduceHp(2, AttackType.NOT_FOUND, EntityType.EPSILON);
                        c = true;
                    }
                }
                for(BlackOrb blackOrb : game.getEnemyController().getBlackOrbs()){
                    for(BlackOrbChuck blackOrbChuck : blackOrb.getBlackOrbChucks()){
                        if(Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(blackOrbChuck, game.getEpsilon())){
                            blackOrbChuck.reduceHp(2, AttackType.NOT_FOUND, EntityType.EPSILON);
                            c = true;
                        }
                    }
                }
                if(c)
                    game.getEpsilon().getEpsilonLogic().setLastWritOfAstrape(System.currentTimeMillis());

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
                            enemy.reduceHp(10, AttackType.MELEE, EntityType.EPSILON);
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
                game.getEpsilon().reduceHp(-1, AttackType.REDUCE_FOR_INCREASE, EntityType.NOF_FOUND);
            }
        }
        if(Constant.levelOfDefend >= 2){
            // I Write This in epsilon (reduce Hp function)
        }
        if(Constant.levelOfDefend >= 3){
            // I Write This is enemy (reduce Hp function)
        }
    }
    public void changeShapeProcess(){
        if(Constant.levelOfChangeShape >= 1){
            if(!writOfProteusOpenBefore){
                addProteus();
                writOfProteusOpenBefore = true;

            }
            if(game.getEpsilon().getEpsilonLogic().isWritOfProteusAvailable()){
                for(Vertex vertex : game.getEpsilon().getEpsilonLogic().getVerticesForProteus()){
                    for(Enemy enemy : game.getEnemyController().getEnemyArrayList()){
                        if(Intersection.isCompletelyInside(vertex, enemy)){
                            enemy.reduceHp(10, AttackType.MELEE, EntityType.EPSILON);
                            enemy.doImpact(vertex.getX(), vertex.getY(), Constant.RADIUS_OF_IMPACT);
                            game.getEpsilon().getEpsilonLogic().setLastWriteOfProteus(System.currentTimeMillis());
                            game.getEpsilon().doImpact(vertex.getX(), vertex.getY(), Constant.RADIUS_OF_IMPACT);
                        }
                    }
                }
            }
        }
        if(Constant.levelOfChangeShape >= 2){
            if(!writOfEmpusaOpenBefore){
                writOfEmpusaOpenBefore = true;
                game.getEpsilon().reduceSizeBy10Percent();
                // reduce epsilon size by %10;
            }
        }
        if(Constant.levelOfChangeShape >= 3){
            if(!writOfDolusOpenBefore){
                writOfDolusOpenBefore = true;
                Random random = new Random();
                int randomAddition = random.nextInt(3);
                randomAddition = Math.abs(randomAddition);
                Constant.levelOfAttack += randomAddition;
                Constant.levelOfDefend += (2 - randomAddition);
            }
        }
    }
    private void addCerberus(){
        Epsilon epsilon = game.getEpsilon();
        Vertex vertex = new Vertex(epsilon.getX() + epsilon.getWidth() - 4, epsilon.getY() + epsilon.getHeight() - 4, 10, epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForCerberus().add(vertex);
        Vertex vertex1 = new Vertex(epsilon.getX() , epsilon.getY() + epsilon.getHeight(), 10, epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForCerberus().add(vertex1);
        Vertex vertex2 = new Vertex(epsilon.getCenterX() - 10, epsilon.getY() - 15, 10, epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForCerberus().add(vertex2);
        epsilon.getCurrentFrame().addToGamePanel(vertex);
        epsilon.getCurrentFrame().addToGamePanel(vertex1);
        epsilon.getCurrentFrame().addToGamePanel(vertex2);
    }
    private void addProteus(){
        Epsilon epsilon = game.getEpsilon();
        Vertex vertex = new Vertex(epsilon.getX() - 12, epsilon.getCenterY() - 15, epsilon.getPowerOfShot(), epsilon.getCurrentFrame());
        game.getEpsilon().getEpsilonLogic().getVerticesForProteus().add(vertex);
        epsilon.getCurrentFrame().addToGamePanel(vertex);
    }

}
