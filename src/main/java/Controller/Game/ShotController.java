package Controller.Game;

import Models.Enemy.Enemy;
import Models.Enemy.Necropick;
import Models.Epsilon.Shot;
import Models.Game;
import java.util.Iterator;

public class ShotController {
    private Game game;
    public ShotController(Game game){
        this.game = game;
    }
    public void checkShot() {
        if (game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1) {
            Shot shot = new Shot(game.getGameFrame().getEpsilon().getX(), game.getGameFrame().getEpsilon().getY());
            shot.setV(game.getInputListener().getxMousePress(), game.getInputListener().getyMousePress());
            game.getGameFrame().getEpsilon().getShots().add(shot);
            game.getGameFrame().getGamePanel().add(shot);
            game.getInputListener().setxMousePress(-1);
            game.getInputListener().setyMousePress(-1);
        }
    }
    public void shotMove(){
        epsilonShotMove();
        necropickShotMove();
    }

    public void epsilonShotMove(){
        Iterator<Shot> shotIterator = game.getGameFrame().getEpsilon().getShots().iterator();
        while (shotIterator.hasNext()) {
            Shot shot = shotIterator.next();
            shot.move(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());
            boolean outOfBounds = false;

            if (shot.getX() <= -40) {
                game.getGameFrame().setBounds(game.getGameFrame().getX() - 6, game.getGameFrame().getY(), game.getGameFrame().getWidth() + 2, game.getGameFrame().getHeight());
                outOfBounds = true;
            } else if (shot.getX() >= game.getGameFrame().getWidth()) {
                game.getGameFrame().setBounds(game.getGameFrame().getX() + 2, game.getGameFrame().getY(), game.getGameFrame().getWidth() + 2, game.getGameFrame().getHeight());
                outOfBounds = true;
            } else if (shot.getY() <= -40) {
                game.getGameFrame().setBounds(game.getGameFrame().getX(), game.getGameFrame().getY() - 6, game.getGameFrame().getWidth(), game.getGameFrame().getHeight() + 2);
                outOfBounds = true;
            } else if (shot.getY() >= game.getGameFrame().getHeight()) {
                game.getGameFrame().setBounds(game.getGameFrame().getX(), game.getGameFrame().getY() + 2, game.getGameFrame().getWidth(), game.getGameFrame().getHeight() + 2);
                outOfBounds = true;
            }

            if (outOfBounds) {
                shotIterator.remove();
                game.getGameFrame().removeShotFromPanel(shot);
                game.getGameFrame().getEpsilon().getShots().remove(shot);
            }
        }
        game.getGameFrame().getPanel().revalidate();
        game.getGameFrame().getPanel().repaint();
        game.getGameFrame().repaint();
    }

    public void necropickShotMove(){
        for (Enemy enemy : game.getEnemyController().getEnemyArrayList()) {
            if (enemy instanceof Necropick) {
                Necropick necropick = (Necropick) enemy;
                Iterator<Shot> shotIterator = necropick.getShots().iterator();
                while (shotIterator.hasNext()) {
                    Shot shot = shotIterator.next();
                    shot.move(game.getGameFrame().getWidth(), game.getGameFrame().getHeight());
                    if (shot.checkRemovedBoard(game.getGameFrame().getWidth(), game.getGameFrame().getHeight())) {
                        shotIterator.remove();
                        game.getGameFrame().removeShotFromPanel(shot);
                        necropick.getShots().remove(shot);
                    }
                }
            }
        }
    }


}
