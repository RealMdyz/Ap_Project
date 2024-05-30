package Controller;

import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.EnemyWave;
import Models.Epsilon.Collectible;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.Epsilon.Vertex;
import Models.Game;
import Models.ObjectsInGame;
import MyProject.MyProjectData;
import View.MusicPlayer;

import java.awt.*;
import java.util.Random;

public class GameLoop{


    private Game game;
    private Constant constant;
    private EnemyWave[] waves_Phase1;
    private int currentWaveIndex;
    private int currentWaveIndexEnemy;
    private int delay = 0;
    private long startOfGame = 0;
    private boolean panelReduced = false;
    private boolean epsilonIncrease = false;
    private boolean panelReducedInEnd = false;
    private boolean addVertex = true;

    public GameLoop(Game game, Constant constant){
        this.constant = constant;
        this.game = game;
        PhaseOne phaseOne = new PhaseOne();
        phaseOne.start();
        
    }


    private class PhaseOne extends Thread{
        public void run() {
            startOfGame = System.currentTimeMillis();
            waves_Phase1 = new EnemyWave[3];
            waves_Phase1[0] = new EnemyWave((int)(Constant.getLevel() / 8) + 1 , 3 * (100 - Constant.getLevel()) * 8 + 1200, 1);
            waves_Phase1[1] = new EnemyWave((int)(Constant.getLevel() / 6) + 1, 2 * (100 - Constant.getLevel()) * 4 + 1000, 1);
            waves_Phase1[2] = new EnemyWave((int)(Constant.getLevel() / 5) + 1, (100 - Constant.getLevel()) * 2 + 800, 1);
            currentWaveIndex = 0;
            currentWaveIndexEnemy = 0;
            // Start the timer for smooth size reduction
            while (Constant.isIsRunning()) {
                game.getStorePanel().setVisible(Constant.isOpenStore());
                if(!Constant.isOpenStore()){
                    update();
                }
                else{
                    store();
                    game.getStorePanel().setXpLabel();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
            game.getMusicPlayer().stop();
            game.getMusicPlayer().close();
            game.getGameFrame().setVisible(false);

        }
        private void update(){
            if(game.getGameFrame().getEpsilon().getHp() <= 0){
                game.endGame();
                Constant.setIsRunning(false);
            }
            if(game.getStorePanel().newImpact){
                doImpact(game.getStorePanel().newImpactX, game.getStorePanel().newImpactY, 500);
                game.getStorePanel().newImpact = false;
                game.getStorePanel().newImpactX = 0;
                game.getStorePanel().newImpactY = 0;

            }
            long t = System.currentTimeMillis();
            if(game.getGameFrame().getEpsilon().getHp() < 100 && Epsilon.isWriteOfAceso() && t - game.getGameFrame().getEpsilon().getPrevAceso() > 1000 && Constant.isqPressed()){
                game.getGameFrame().getEpsilon().setHp(game.getGameFrame().getEpsilon().getHp() + 1);
                game.getGameFrame().getEpsilon().setPrevAceso(System.currentTimeMillis());
            }
            if(addVertex && Constant.isqPressed()){
                game.getGameFrame().getEpsilon().addVertex();
                for(Vertex vertex : game.getGameFrame().getEpsilon().getVertices()){
                    game.getGameFrame().addToGamePanel(vertex);
                    // System.out.println(vertex.getX() + " " + vertex.getY());
                    // System.out.println(game.getGameFrame().getEpsilon().getVertices().size());
                }
                addVertex = false;
            }
            game.getGameFrame().getEpsilon().move(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());
            game.getGameFrame().repaint();

            if(game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1){
                Shot shot = new Shot(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
                shot.setV(game.getInputListener().getxMousePress(), game.getInputListener().getyMousePress());
                if(Constant.isqPressed())
                    shot.setPower(5 + Epsilon.getLevelOfWritOfAres());
                game.getGameFrame().getShots().add(shot);
                game.getGameFrame().getGamePanel().add(shot);
                if(System.currentTimeMillis() - game.getGameFrame().getEpsilon().getStartOfAthena() < 10000){
                    Shot shot1 = new Shot(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
                    shot1.setV(game.getInputListener().getxMousePress() + 20, game.getInputListener().getyMousePress() + 20);
                    if(Constant.isqPressed())
                        shot1.setPower(5 + Epsilon.getLevelOfWritOfAres());
                    game.getGameFrame().getShots().add(shot1);
                    game.getGameFrame().getGamePanel().add(shot1);
                    Shot shot2 = new Shot(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
                    shot2.setV(game.getInputListener().getxMousePress() - 20, game.getInputListener().getyMousePress() - 20);
                    if(Constant.isqPressed())
                        shot2.setPower(5 + Epsilon.getLevelOfWritOfAres());
                    game.getGameFrame().getShots().add(shot2);
                    game.getGameFrame().getGamePanel().add(shot2);
                }
                game.getInputListener().setxMousePress(-1);
                game.getInputListener().setyMousePress(-1);
            }
            updateTopPanel();
            //    game.getGameFrame().getEpsilon().rotateVertices(5);
            if(currentWaveIndex < 3){
                shotMove();
                enemyMove();
                Random random = new Random();
                if(random.nextInt() % (220 - Constant.getLevel() * 2) == 1)
                    aggression();
                checkIntersection();
                checkTheImpact();
                delay += 15;
                if(delay >= waves_Phase1[currentWaveIndex].getDelay()){
                    addNewEnemy();

                }
            }
            game.getGameFrame().checkTheCollectibleTime();
            if(currentWaveIndex == 3){
                if(epsilonIncrease){
                    if(panelReducedInEnd){
                        Constant.setIsRunning(false);
                        game.endGame();
                    }
                    else{
                        game.getGameFrame().setSize(game.getGameFrame().getWidth() - 3, game.getGameFrame().getHeight() - 3);
                        if(game.getGameFrame().getHeight() <= 50){
                            panelReducedInEnd = true;
                        }
                        // System.out.println(game.getGameFrame().getHeight());
                    }
                }
                else{
                    game.getGameFrame().getEpsilon().changeSize(
                            game.getGameFrame().getEpsilon().getWidth() + 3,
                            game.getGameFrame().getEpsilon().getHeight() + 3
                    );
                    if(game.getGameFrame().getEpsilon().getHeight() > game.getGameFrame().getHeight())
                        epsilonIncrease = true;
                }



            }
            try {
                Thread.sleep(20);
            }
            catch (Exception e){

            }
        }
        private void store(){

            try {
                Thread.sleep(100);
            }
            catch (Exception ignored){

            }
        }
        private void addNewEnemy(){
            MusicPlayer.playOnce(MyProjectData.getProjectData().getEnterAnEnemy());
            int index = 0;
            for(Enemy objects : waves_Phase1[currentWaveIndex].getEnemies()){
                index += 1;
                // objects.rotateImage(20);
                game.getGameFrame().add(objects);

                if(index > currentWaveIndexEnemy){
                    currentWaveIndexEnemy += 1;
                    break;
                }
            }
            delay = 0;
            if(currentWaveIndexEnemy >= waves_Phase1[currentWaveIndex].getEnemies().size()){
                waves_Phase1[currentWaveIndex].setWaveOver(true);
                for(ObjectsInGame objects : waves_Phase1[currentWaveIndex].getEnemies()){
                    if(objects.getHp() > 0){
                        waves_Phase1[currentWaveIndex].setWaveOver(false);
                    }
                }
                if(waves_Phase1[currentWaveIndex].isWaveOver()){
                    currentWaveIndex += 1;
                    currentWaveIndexEnemy = 0;
                    MusicPlayer.playOnce(MyProjectData.getProjectData().getEndOfEachWaveSound());
                }
            }
        }
        private void shotMove(){
            Shot removedShot = new Shot(0, 0);
            int cnt = 0;
            for(Shot shot : game.getGameFrame().getShots()){
                shot.move(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());
                if(shot.getX() <= -40){
                    game.getGameFrame().setBounds(game.getGameFrame().getX() - 6, game.getGameFrame().getY(), game.getGameFrame().getWidth() + 2, game.getGameFrame().getHeight());
                    removedShot = shot;
                    cnt = 1;
                }

                if(shot.getX() >= game.getGameFrame().getWidth()){
                    game.getGameFrame().setBounds(game.getGameFrame().getX() + 2, game.getGameFrame().getY(), game.getGameFrame().getWidth() + 2, game.getGameFrame().getHeight());
                    removedShot = shot;
                    cnt = 1;
                }
                if(shot.getY() <= -40){
                    game.getGameFrame().setBounds(game.getGameFrame().getX(), game.getGameFrame().getY() - 6, game.getGameFrame().getWidth(), game.getGameFrame().getHeight() + 2);
                    removedShot = shot;
                    cnt = 1;
                }
                if(shot.getY() >= game.getGameFrame().getHeight()){
                    game.getGameFrame().setBounds(game.getGameFrame().getX(), game.getGameFrame().getY() + 2, game.getGameFrame().getWidth(), game.getGameFrame().getHeight() + 2);
                    removedShot = shot;
                    cnt = 1;
                }
                game.getGameFrame().repaint();
            }
            if(cnt == 1)
                game.getGameFrame().removeOneShot(removedShot);

        }

        private void enemyMove(){
            int index = 0;
            for(Enemy object: waves_Phase1[currentWaveIndex].getEnemies()){
                index += 1;
                if(index > currentWaveIndexEnemy)
                    break;;
                object.localRouting(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
                object.move(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());
                game.getGameFrame().repaint();
            }
        }
        private void checkIntersection(){
            int index = 0;
            Enemy enemy1 = new Enemy(0, 0, 1, 0, 0, 0, 0);
            Shot shot1 = new Shot(0,0 );
            for(Enemy enemy : waves_Phase1[currentWaveIndex].getEnemies()){
                index += 1;
                if(index > currentWaveIndexEnemy)
                    break;
                for(Shot shot : game.getGameFrame().getShots()){
                    if(game.getIntersection().checkCollision(shot, enemy) && shot.getHp() == 1){
                        enemy.setHp(enemy.getHp() - shot.getPower());
                        shot.setHp(0);
                        shot1 = shot;
                        if(enemy.getHp() <= 0){
                            enemy1 = enemy;
                            game.getGameFrame().remove(enemy);
                            game.getGameFrame().addNewCollectoble(enemy);
                            MusicPlayer.playOnce(MyProjectData.getProjectData().getEnemyDieSound());
                            game.getGameFrame().revalidate();
                            game.getGameFrame().repaint();
                            currentWaveIndexEnemy -= 1;
                        }

                    }
                }

            }
            waves_Phase1[currentWaveIndex].getEnemies().remove(enemy1);
            game.getGameFrame().repaint();
            game.getGameFrame().removeOneShot(shot1);

            Collectible collectible1 = new Collectible(0, 0, 0, 0, 0);
            for(Collectible collectible : game.getGameFrame().getCollectibles()){
                if(game.getIntersection().intersect(collectible, game.getGameFrame().getEpsilon())){
                    Constant.setPlayerXP(Constant.getPlayerXP() + collectible.getIncreaceXp());
                    collectible1 = collectible;
                }
            }
            game.getGameFrame().getCollectibles().remove(collectible1);
            game.getGameFrame().getGamePanel().remove(collectible1);
            game.getGameFrame().getGamePanel().repaint();
        }
        private void checkTheImpact(){
            int index = 0;
            Enemy enemy12 = new Enemy(0, 0, 1, 0, 0, 0, 0);

            for(Enemy enemy : waves_Phase1[currentWaveIndex].getEnemies()){
                index += 1;
                if(index > currentWaveIndexEnemy)
                    break;
                if(Constant.isqPressed()){ // Thhiiiiiiiiiiiiiis Where!!
                    for(Vertex vertex : game.getGameFrame().getEpsilon().getVertices()){
                        if(game.getIntersection().intersect(vertex, enemy)){
                            doImpact(vertex.getxCenter(),vertex.getyCenter(), 100);
                            enemy.setHp(enemy.getHp() - 10);
                            if(enemy.getHp() <= 0){
                                enemy12 = enemy;
                                game.getGameFrame().remove(enemy);
                                game.getGameFrame().addNewCollectoble(enemy);
                                MusicPlayer.playOnce(MyProjectData.getProjectData().getEnemyDieSound());
                                game.getGameFrame().revalidate();
                                game.getGameFrame().repaint();
                                currentWaveIndexEnemy -= 1;
                            }
                        }
                    }
                }
          /*  if(game.getIntersection().intersect(enemy, game.getGameFrame().getEpsilon())){
                doImpact(game.getIntersection().getIntersectionCenter(game.getGameFrame().getEpsilon(), enemy).x, game.getIntersection().getIntersectionCenter(game.getGameFrame().getEpsilon(), enemy).y , 100);
                game.getGameFrame().getEpsilon().setHp(game.getGameFrame().getEpsilon().getHp() - enemy.getPower());
            }*/
                if(game.getIntersection().intersectInTheEdge(game.getGameFrame().getEpsilon(), enemy) != null){
                    Point point = game.getIntersection().intersectInTheEdge(game.getGameFrame().getEpsilon(), enemy);
                    doImpact(point.x, point.y, 100);
                }
                else if(game.getIntersection().checkIntersectInPoint(enemy, game.getGameFrame().getEpsilon()) != null){
                    Point point = game.getIntersection().checkIntersectInPoint(enemy, game.getGameFrame().getEpsilon());
                    doImpact(point.x, point.y, 100);
                    game.getGameFrame().getEpsilon().setHp(game.getGameFrame().getEpsilon().getHp() - enemy.getPower());
                }



                int secondIndex = 0;
                for(Enemy enemy1 : waves_Phase1[currentWaveIndex].getEnemies()){
                    secondIndex += 1;
                    if(secondIndex > currentWaveIndexEnemy)
                        break;
                    if(secondIndex > index){
                        if(game.getIntersection().intersect(enemy1, enemy)){
                            doImpact(game.getIntersection().getIntersectionCenter(enemy, enemy1).x, game.getIntersection().getIntersectionCenter(enemy, enemy1).y, 100);
                        }
                    }
                }
            }
            waves_Phase1[currentWaveIndex].getEnemies().remove(enemy12);
            game.getGameFrame().repaint();
            game.getGameFrame().getEpsilon().doImpactToWall(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());

        }
        private void doImpact(int x, int y, int dis){
            for(Enemy enemy : waves_Phase1[currentWaveIndex].getEnemies()){
                enemy.doImpact(x, y, dis);

            }
            game.getGameFrame().getEpsilon().doImpact(x, y, dis);
        }
        private void updateTopPanel(){
            game.getTopPanel().updateHPLabel(game.getGameFrame().getEpsilon().getHp());
            game.getTopPanel().updateXPLabel(constant.getPlayerXP());
            game.getTopPanel().updateTimeLabel((System.currentTimeMillis() - startOfGame));
            game.getTopPanel().updateWaveLabel(currentWaveIndex);
            if (!panelReduced && game.getGameFrame().getWidth() > 450 && game.getGameFrame().getHeight() > 450) {
                int newWidth = game.getGameFrame().getWidth() - 4;
                int newHeight = game.getGameFrame().getHeight() - 4;
                int x = (Toolkit.getDefaultToolkit().getScreenSize().width - newWidth) / 2;
                int y = (Toolkit.getDefaultToolkit().getScreenSize().height - newHeight) / 2;
                game.getGameFrame().setBounds(x, y, newWidth, newHeight);
                if (newWidth <= 450 && newHeight <= 450) {
                    panelReduced = true; // Set the flag once the panel size is reduced
                }
            }
        }
        private void aggression(){
            int index = 0;
            for(Enemy enemy : waves_Phase1[currentWaveIndex].getEnemies()){
                index += 1;
                if(index > currentWaveIndexEnemy)
                    break;
                if(enemy.getHp() == 10){
                    Enemy enemy2 = enemy;
                    int xDash = 15;
                    int yDash = 15;
                    if(game.getGameFrame().getEpsilon().getX() < enemy2.getX())
                        xDash *= (-1);
                    enemy2.addX(xDash);

                    if(game.getGameFrame().getEpsilon().getY() < enemy2.getY())
                        yDash *= (-1);
                    enemy2.addY(yDash);
                    boolean c = true;
                    int secondIndex = 0;
                    for(Enemy enemy1 :  waves_Phase1[currentWaveIndex].getEnemies()){
                        secondIndex += 1;
                        if(index <= secondIndex ){
                            break;
                        }
                        if(game.getIntersection().intersect(enemy1, enemy2))
                            c = false;
                    }
                    if(game.getIntersection().intersect(game.getGameFrame().getEpsilon(), enemy2))
                        c = false;

                    if(c){
                        enemy.addX(xDash);
                        enemy.addY(yDash);
                        game.getGameFrame().repaint();
                        break;
                    }
                }
            }
        }
    }



}
