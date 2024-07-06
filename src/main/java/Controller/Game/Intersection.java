package Controller.Game;

import Models.Constant;
import Models.Enemy.Aoe;
import Models.Enemy.Enemy;
import Models.Epsilon.Epsilon;
import Models.Epsilon.Shot;
import Models.ObjectsInGame;

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
        return distance < (shotRadius + objectRadius);
    }
    public boolean checkTheIntersectionBetweenAObjectInGameAndAObjectInGame(ObjectsInGame object1, ObjectsInGame object2) {
        // Get the bounding boxes of the objects
        Rectangle bounds1 = new Rectangle(object1.getX(), object1.getY(), object1.getWidth(), object1.getHeight());
        Rectangle bounds2 = new Rectangle(object2.getX(), object2.getY(), object2.getWidth(), object2.getHeight());

        // Check if the bounding boxes intersect
        return bounds1.intersects(bounds2);
    }

    public static boolean isInAoE(ObjectsInGame objectsInGame, Aoe aoe) {
        int epsilonX = objectsInGame.getX();
        int epsilonY = objectsInGame.getY();
        int epsilonWidth = objectsInGame.getWidth();
        int epsilonHeight = objectsInGame.getHeight();

        return epsilonX + epsilonWidth > aoe.getX() && epsilonX < aoe.getX() + aoe.getWidth() &&
                epsilonY + epsilonHeight > aoe.getY() && epsilonY < aoe.getY() + aoe.getHeight();
    }
}

