package Controller.Game;

import Controller.BossFight.BossFightManger;
import Models.AttackType;
import Models.EntityType;
import Models.Epsilon.EpsilonLogic;
import Models.Epsilon.Shot;
import Models.Game;
import View.Game.GameFrame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EpsilonController {
    boolean firstTime = true;
    EpsilonLogic epsilonLogic;
    public EpsilonController(EpsilonLogic epsilonLogic) {
        this.epsilonLogic = epsilonLogic;
    }

    public void setTheEpsilonFrameSize(Game game) {
        if (firstTime || Math.random() < 0.04) {
            reduceEpsilonFrameSize(game);
        }
    }
    protected void fireShot(Game game){
        if (game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1) {
            Shot shot = new Shot(game.getEpsilon().getX(), game.getEpsilon().getY(), game.getEpsilon().getPowerOfShot(), game.getEpsilon().getCurrentFrame(), true);
            if(epsilonLogic.isInPhonoiSlaughter())
                shot.setPower(50);
            epsilonLogic.setInPhonoiSlaughter(false);
            game.getEpsilon().getCurrentFrame().add(shot);
            game.getEpsilon().getShots().add(shot);
            shot.setV(game.getInputListener().getxMousePress(), game.getInputListener().getyMousePress());
            game.getInputListener().setxMousePress(-1);
            game.getInputListener().setyMousePress(-1);
        }
    }
    public void reduceEpsilonFrameSize(Game game) {
        GameFrame epsilonFrame = game.getEpsilonFrame();
        int frameWidth = epsilonFrame.getWidth();
        int frameHeight = epsilonFrame.getHeight();
        int reductionAmount = 4;
        if (frameWidth > 400  && frameHeight > 400) {
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
    public boolean isCollidingWithFrameEdge(Shot shot, GameFrame epsilonFrame) {
        int shotX = shot.getX();
        int shotY = shot.getY();
        int frameWidth = epsilonFrame.getWidth();
        int frameHeight = epsilonFrame.getHeight();

        return shotX <= 0 || shotX >= frameWidth ||
                shotY <= 0 || shotY >= frameHeight ;
    }

    public void handelEpsilonShotInBossFight(Game game){
        ArrayList<Shot> shotArrayList = new ArrayList<>();
        if (game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1) {
            Shot shot = new Shot(game.getEpsilon().getX(), game.getEpsilon().getY(), game.getEpsilon().getPowerOfShot(), game.getEpsilon().getCurrentFrame(), true);
            if(game.getEpsilon().getEpsilonLogic().isInPhonoiSlaughter())
                shot.setPower(50);
            game.getEpsilon().getEpsilonLogic().setInPhonoiSlaughter(false);
            game.getEpsilon().getCurrentFrame().add(shot);
            game.getEpsilon().getShots().add(shot);
            shot.setV(game.getInputListener().getxMousePress(), game.getInputListener().getyMousePress());
            game.getInputListener().setxMousePress(-1);
            game.getInputListener().setyMousePress(-1);
        }
        for(Shot shot : game.getEpsilon().getShots()){
            shot.move();
            shot.repaint();
            shot.getCurrentFrame().repaint();
            for(GameFrame gameFrame : game.getGameFrames()){
                if(Intersection.isInThisFrame(shot, gameFrame) && !shot.getCurrentFrame().equals(gameFrame)){
                    shot.changeFrameAndPaint(gameFrame);
                }
            }
            if(shot.getCurrentFrame().equals(game.getEpsilon().getCurrentFrame()) && enlargeEpsilonFrame(game, shot)){
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
            else if (shot.getXRelativeToTheScreen() < -200 || shot.getXRelativeToTheScreen() > 2500 || shot.getYRelativeToTheScreen() < -200 || shot.getYRelativeToTheScreen()> 2500){
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
            else if(BossFightManger.isOpenAttackToSmileyFace() && Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(shot, game.getBossFightManger().getSmileyFace())){
                game.getBossFightManger().getSmileyFace().reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
            else if(BossFightManger.isOpenAttackToSmileyHands() && Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(shot, game.getBossFightManger().getSmileyLeftHand())){
                game.getBossFightManger().getSmileyLeftHand().reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
            else if(BossFightManger.isOpenAttackToSmileyHands() && Intersection.checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(shot, game.getBossFightManger().getSmileyRightHand())){
                game.getBossFightManger().getSmileyRightHand().reduceHp(shot.getPower(), AttackType.RANGED, EntityType.EPSILON);
                shotArrayList.add(shot);
                shot.getCurrentFrame().removeFromGamePanel(shot);
            }
        }
        game.getEpsilon().getShots().removeAll(shotArrayList);

    }

    public boolean enlargeEpsilonFrame(Game game, Shot shot) {
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
            for(GameFrame gameFrame : game.getGameFrames()){
                if(gameFrame.isSolb() && FrameIntersection.twoFrameIntersection(epsilonFrame, gameFrame)){
                    epsilonFrame.setSize(frameWidth - movementAmount, frameHeight);
                    epsilonFrame.setLocation(epsilonFrame.getX() + enlargementAmount, epsilonFrame.getY());
                    return false;
                }
            }
            epsilonFrame.getGamePanel().setSize(epsilonFrame.getWidth(), epsilonFrame.getHeight());
            epsilonFrame.revalidate();
            epsilonFrame.repaint();
            return true;
        }
        else if (shotX >= frameWidth - shot.getWidth()) {
            // برخورد به دیواره سمت راست
            epsilonFrame.setSize(frameWidth + enlargementAmount, frameHeight);
            epsilonFrame.setLocation(epsilonFrame.getX() + movementAmount, epsilonFrame.getY());

            for(GameFrame gameFrame : game.getGameFrames()){
                if(gameFrame.isSolb() && FrameIntersection.twoFrameIntersection(epsilonFrame, gameFrame)){
                    epsilonFrame.setSize(frameWidth - enlargementAmount, frameHeight);
                    epsilonFrame.setLocation(epsilonFrame.getX() - movementAmount, epsilonFrame.getY());
                    return false;
                }
            }
            epsilonFrame.getGamePanel().setSize(epsilonFrame.getWidth(), epsilonFrame.getHeight());
            epsilonFrame.revalidate();
            epsilonFrame.repaint();
            return true;
        } else if (shotY <= 0) {
            // برخورد به دیواره بالا
            epsilonFrame.setSize(frameWidth, frameHeight + movementAmount);
            epsilonFrame.setLocation(epsilonFrame.getX(), epsilonFrame.getY() - enlargementAmount);
            for(GameFrame gameFrame : game.getGameFrames()){
                if(gameFrame.isSolb() && FrameIntersection.twoFrameIntersection(epsilonFrame, gameFrame)){
                    epsilonFrame.setSize(frameWidth, frameHeight - movementAmount);
                    epsilonFrame.setLocation(epsilonFrame.getX(), epsilonFrame.getY() + enlargementAmount);
                    return false;
                }
            }
            epsilonFrame.getGamePanel().setSize(epsilonFrame.getWidth(), epsilonFrame.getHeight());
            epsilonFrame.revalidate();
            epsilonFrame.repaint();
            return true;
        } else if (shotY >= frameHeight - shot.getHeight()) {
            // برخورد به دیواره پایین
            epsilonFrame.setSize(frameWidth, frameHeight + enlargementAmount);
            epsilonFrame.setLocation(epsilonFrame.getX(), epsilonFrame.getY() + movementAmount);
            for(GameFrame gameFrame : game.getGameFrames()){
                if(gameFrame.isSolb() && FrameIntersection.twoFrameIntersection(epsilonFrame, gameFrame)){
                    epsilonFrame.setSize(frameWidth, frameHeight - enlargementAmount);
                    epsilonFrame.setLocation(epsilonFrame.getX(), epsilonFrame.getY() - movementAmount);
                    return false;
                }
            }
            epsilonFrame.getGamePanel().setSize(epsilonFrame.getWidth(), epsilonFrame.getHeight());
            epsilonFrame.revalidate();
            epsilonFrame.repaint();
            return true;
        }


        return false;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
