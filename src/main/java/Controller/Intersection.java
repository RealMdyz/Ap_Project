package Controller;

import Models.Enemy.Enemy;
import Models.Epsilon.Shot;
import Models.ObjectsInGame;

import java.awt.*;

public class Intersection {

    public Intersection(){

    }
    //   Check collision between a shot (rectangle) and an enemy (point)
    public boolean checkCollision(Shot shot, Enemy enemy) {
        // Calculate the distance from the center of the enemy to each side of the rectangle
        double distX = Math.abs(enemy.getX() - (shot.getX() + shot.getWidth() / 2.0));
        double distY = Math.abs(enemy.getY() - (shot.getY() + shot.getHeight() / 2.0));

        // Check if the distance is less than half the width and half the height of the rectangle
        if (distX > (shot.getWidth() / 2.0)) return false;
        if (distY > (shot.getHeight() / 2.0)) return false;

        return true;
    }
    public boolean intersect(ObjectsInGame obj1, ObjectsInGame obj2) {
        // Calculate the coordinates of the bounding boxes for obj1
        int obj1X1 = obj1.getX();
        int obj1Y1 = obj1.getY();
        int obj1X2 = obj1X1 + obj1.getWidth();
        int obj1Y2 = obj1Y1 + obj1.getHeight();

        // Calculate the coordinates of the bounding boxes for obj2
        int obj2X1 = obj2.getX();
        int obj2Y1 = obj2.getY();
        int obj2X2 = obj2X1 + obj2.getWidth();
        int obj2Y2 = obj2Y1 + obj2.getHeight();

        // Calculate intersection area
        int intersectionArea = Math.max(0, Math.min(obj1X2, obj2X2) - Math.max(obj1X1, obj2X1)) *
                Math.max(0, Math.min(obj1Y2, obj2Y2) - Math.max(obj1Y1, obj2Y1));

        // If intersection area is greater than zero, rectangles are intersecting
        return intersectionArea > 0;
    }
    // Check intersection between two ObjectsInGame objects and return the center point of the intersection area
    public Point getIntersectionCenter(ObjectsInGame obj1, ObjectsInGame obj2) {
        // Calculate the intersection rectangle
        Rectangle intersection = intersectRectangle(obj1, obj2);

        // If there is no intersection, return null
        if (intersection == null) {
            return null;
        }

        // Calculate center point of the intersection rectangle
        int centerX = intersection.x + intersection.width / 2;
        int centerY = intersection.y + intersection.height / 2;

        return new Point(centerX, centerY);
    }

    // Calculate the intersection rectangle between two ObjectsInGame objects
    private Rectangle intersectRectangle(ObjectsInGame obj1, ObjectsInGame obj2) {
        // Calculate the coordinates of the bounding boxes for obj1
        int obj1X1 = obj1.getX();
        int obj1Y1 = obj1.getY();
        int obj1X2 = obj1X1 + obj1.getWidth();
        int obj1Y2 = obj1Y1 + obj1.getHeight();

        // Calculate the coordinates of the bounding boxes for obj2
        int obj2X1 = obj2.getX();
        int obj2Y1 = obj2.getY();
        int obj2X2 = obj2X1 + obj2.getWidth();
        int obj2Y2 = obj2Y1 + obj2.getHeight();

        // Calculate intersection coordinates
        int interLeft = Math.max(obj1X1, obj2X1);
        int interTop = Math.max(obj1Y1, obj2Y1);
        int interRight = Math.min(obj1X2, obj2X2);
        int interBottom = Math.min(obj1Y2, obj2Y2);

        // Check if intersection exists
        if (interLeft < interRight && interTop < interBottom) {
            return new Rectangle(interLeft, interTop, interRight - interLeft, interBottom - interTop);
        }

        // No intersection, return null
        return null;
    }

}

