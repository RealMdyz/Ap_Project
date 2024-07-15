package Controller.Game;

import Models.Enemy.Enemy;
import Models.Enemy.Normal.Aoe;
import Models.Epsilon.Shot;
import Models.ObjectsInGame;
import View.Game.GameFrame;

import java.awt.*;

public class Intersection {

    public Intersection(){

    }
    public boolean checkTheIntersectionBetweenAShotAndAObjectInGame(ObjectsInGame objectsInGame, Shot shot) {
        // Calculate the center of the shot
        int shotCenterX = shot.getX() + shot.getWidth() / 2;
        int shotCenterY = shot.getY() + shot.getHeight() / 2;
        int shotRadius = Math.min(shot.getWidth(), shot.getHeight()) / 2;

        // Calculate the center of the object in game
        int objectCenterX = objectsInGame.getX() + objectsInGame.getWidth() / 2;
        int objectCenterY = objectsInGame.getY() + objectsInGame.getHeight() / 2;
        int objectRadius = Math.min(objectsInGame.getWidth(), objectsInGame.getHeight()) / 2;

        // Calculate the distance between the centers
        double distance = Math.sqrt(Math.pow(shotCenterX - objectCenterX, 2) + Math.pow(shotCenterY - objectCenterY, 2));

        // Check if the distance is less than the sum of the radii
        return distance < (shotRadius + objectRadius) && shot.getCurrentFrame().equals(objectsInGame.getCurrentFrame());
    }
    public static boolean checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(ObjectsInGame object1, ObjectsInGame object2) {
        // Get the bounding boxes of the objects
        Rectangle bounds1 = new Rectangle(object1.getX(), object1.getY(), object1.getWidth(), object1.getHeight());
        Rectangle bounds2 = new Rectangle(object2.getX(), object2.getY(), object2.getWidth(), object2.getHeight());

        // Check if the bounding boxes intersect
        return bounds1.intersects(bounds2) && object1.getCurrentFrame().equals(object2.getCurrentFrame());
    }

    public static boolean isInAoE(ObjectsInGame objectsInGame, Aoe aoe) {
        int epsilonX = objectsInGame.getX();
        int epsilonY = objectsInGame.getY();
        int epsilonWidth = objectsInGame.getWidth();
        int epsilonHeight = objectsInGame.getHeight();

        return epsilonX + epsilonWidth > aoe.getX() && epsilonX < aoe.getX() + aoe.getWidth() &&
                epsilonY + epsilonHeight > aoe.getY() && epsilonY < aoe.getY() + aoe.getHeight();
    }
    public static boolean isCompletelyInside(ObjectsInGame object1, ObjectsInGame object2) {
        // گرفتن مختصات چهار گوشه شیء داخلی (this)
        int thisLeft = object1.getX();
        int thisRight = object1.getX() + object1.getWidth();
        int thisTop = object1.getY();
        int thisBottom = object1.getY() + object1.getHeight();

        // گرفتن مختصات چهار گوشه شیء خارجی (other)
        int otherLeft = object2.getX();
        int otherRight = object2.getX() + object2.getWidth();
        int otherTop = object2.getY();
        int otherBottom = object2.getY() + object2.getHeight();

        // بررسی اینکه آیا هر چهار گوشه شیء داخلی درون شیء خارجی قرار دارند
        return thisLeft >= otherLeft &&
                thisRight <= otherRight &&
                thisTop >= otherTop &&
                thisBottom <= otherBottom;
    }

    public boolean isInThisFrame(Enemy enemy, GameFrame gameFrame){
        int xNesbatBeWindowOfEnemy = enemy.getX() + enemy.getCurrentFrame().getX();
        int yNesbatBeWindowOfEnemy = enemy.getY() + enemy.getCurrentFrame().getY();
        int widthEnemy = enemy.getWidth();
        int heightEnemy = enemy.getHeight();
        int xGameFrame = gameFrame.getX();
        int yGameFrame = gameFrame.getY();
        int widthGameFrame = gameFrame.getWidth();
        int heightGameFrame = gameFrame.getHeight();
        Rectangle enemyBounds = new Rectangle(xNesbatBeWindowOfEnemy, yNesbatBeWindowOfEnemy, widthEnemy, heightEnemy);
        Rectangle frameBounds = new Rectangle(xGameFrame, yGameFrame, widthGameFrame, heightGameFrame);

        return enemyBounds.intersects(frameBounds);
    }
}

