package Controller.Game;

import Models.Epsilon.Shot;
import Models.Game;
import View.Game.GameFrame;

import java.util.ArrayList;
import java.util.Iterator;

public class EpsilonController {
    boolean firstTime = true;

    public EpsilonController() {
    }

    public void setTheEpsilonFrameSize(Game game) {
        ArrayList<Shot> removedShots = new ArrayList<>();
        synchronized (this) {
            Iterator<Shot> iterator = game.getEpsilon().getShots().iterator();
            for(Shot shot : game.getEpsilon().getShots()){
                if (isCollidingWithFrameEdge(shot, game.getEpsilonFrame()) && !isCollidingWithRigidFrame(shot, game)) {
                    enlargeEpsilonFrame(game, shot);
                    shot.getCurrentFrame().removeFromGamePanel(shot);
                    removedShots.add(shot);
                }
            }
        }

        synchronized (this) {
            game.getEpsilon().getShots().removeAll(removedShots);
        }

        if (firstTime || Math.random() < 0.05) {
            reduceEpsilonFrameSize(game);
        }
    }
    private void reduceEpsilonFrameSize(Game game) {
        GameFrame epsilonFrame = game.getEpsilonFrame();
        int frameWidth = epsilonFrame.getWidth();
        int frameHeight = epsilonFrame.getHeight();
        int reductionAmount = 4;

        // فقط اگر سایز فریم بیشتر از 400x400 باشد، آن را کوچک کنیم
        if (frameWidth > 450  && frameHeight > 450) {
            epsilonFrame.setSize(frameWidth - reductionAmount, frameHeight - reductionAmount);
            epsilonFrame.setLocation(epsilonFrame.getX() + reductionAmount / 2, epsilonFrame.getY() + reductionAmount / 2);

            epsilonFrame.getGamePanel().setSize(epsilonFrame.getWidth(), epsilonFrame.getHeight());
            epsilonFrame.revalidate();
            epsilonFrame.repaint();
        }
        else{
            firstTime = false;
        }
    }
    private boolean isCollidingWithFrameEdge(Shot shot, GameFrame epsilonFrame) {
        int shotX = shot.getX();
        int shotY = shot.getY();
        int frameWidth = epsilonFrame.getWidth();
        int frameHeight = epsilonFrame.getHeight();

        // بررسی برخورد شلیک با دیواره‌های فریم
        return shotX <= 0 || shotX >= frameWidth - shot.getWidth() ||
                shotY <= 0 || shotY >= frameHeight - shot.getHeight();
    }

    private boolean isCollidingWithRigidFrame(Shot shot, Game game) {
        for (GameFrame frame : game.getGameFrames()) {
            if (frame.isSolb() && frame.getBounds().intersects(shot.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private void enlargeEpsilonFrame(Game game, Shot shot) {
        GameFrame epsilonFrame = game.getEpsilonFrame();
        int shotX = shot.getX();
        int shotY = shot.getY();
        int frameWidth = epsilonFrame.getWidth();
        int frameHeight = epsilonFrame.getHeight();
        int enlargementAmount = 6;
        int movementAmount = 3;

        if (shotX <= 0) {
            // برخورد به دیواره سمت چپ
            epsilonFrame.setSize(frameWidth + movementAmount, frameHeight);
            epsilonFrame.setLocation(epsilonFrame.getX() - enlargementAmount, epsilonFrame.getY());
        } else if (shotX >= frameWidth - shot.getWidth()) {
            // برخورد به دیواره سمت راست
            epsilonFrame.setSize(frameWidth + enlargementAmount, frameHeight);
            epsilonFrame.setLocation(epsilonFrame.getX() + movementAmount, epsilonFrame.getY());
        } else if (shotY <= 0) {
            // برخورد به دیواره بالا
            epsilonFrame.setSize(frameWidth, frameHeight + movementAmount);
            epsilonFrame.setLocation(epsilonFrame.getX(), epsilonFrame.getY() - enlargementAmount);
        } else if (shotY >= frameHeight - shot.getHeight()) {
            // برخورد به دیواره پایین
            epsilonFrame.setSize(frameWidth, frameHeight + enlargementAmount);
            epsilonFrame.setLocation(epsilonFrame.getX(), epsilonFrame.getY() + movementAmount);
        }

        epsilonFrame.getGamePanel().setSize(epsilonFrame.getWidth(), epsilonFrame.getHeight());
        epsilonFrame.revalidate();
        epsilonFrame.repaint();
    }
}
