package Controller;

import Models.Enemy.Enemy;
import Models.Epsilon.Epsilon;
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

        double dis = Math.sqrt((obj2.getX() - obj1.getX()) * (obj2.getX() - obj1.getX()) + (obj2.getY() - obj1.getY()) * (obj2.getY() - obj1.getY()));
        if(dis > 100)
            return false;
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

    // Check if any regular polygon intersects with the epsilon
    public boolean intersectsEpsilon(Enemy polygon, Epsilon epsilon) {
        // Calculate the coordinates of the polygon's vertices
        int numSides = polygon.getNumSides(); // Get the number of sides of the polygon
        int[] polyVerticesX = new int[numSides];
        int[] polyVerticesY = new int[numSides];

        // Calculate the size of each side of the polygon
        double sideLength = polygon.getWidth() / 2; // Assuming the polygon is inscribed in a square

        // Calculate the angle increment for each vertex
        double angleIncrement = 2 * Math.PI / numSides;

        // Calculate coordinates of each vertex
        for (int i = 0; i < numSides; i++) {
            double angle = i * angleIncrement;
            int x = (int) (polygon.getX() + sideLength * Math.cos(angle));
            int y = (int) (polygon.getY() + sideLength * Math.sin(angle));
            polyVerticesX[i] = x;
            polyVerticesY[i] = y;
        }

        // Check each vertex against the epsilon
        for (int i = 0; i < numSides; i++) {
            // Calculate the distance between the vertex and the center of the epsilon
            double dx = polyVerticesX[i] - epsilon.getX();
            double dy = polyVerticesY[i] - epsilon.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            // If the distance is less than the epsilon's radius, intersection occurs
            if (distance < epsilon.getRadius()) {
                return true;
            }
        }

        // Check if any point on the epsilon's edge intersects with the polygon
        for (int i = 0; i < numSides; i++) {
            int x1 = polyVerticesX[i];
            int y1 = polyVerticesY[i];
            int x2 = polyVerticesX[(i + 1) % numSides]; // Next vertex (cyclically)
            int y2 = polyVerticesY[(i + 1) % numSides]; // Next vertex (cyclically)

            // Check if the edge of the epsilon intersects with the polygon
            if (edgeIntersectsPolygon(x1, y1, x2, y2, polygon)) {
                return true;
            }
        }

        return false;
    }

    // Check if an edge of the epsilon intersects with the polygon
    private boolean edgeIntersectsPolygon(int x1, int y1, int x2, int y2, Enemy polygon) {
        // Check each edge of the polygon
        int numSides = polygon.getNumSides();
        int[] polyVerticesX = new int[numSides];
        int[] polyVerticesY = new int[numSides];
        double sideLength = polygon.getWidth() / 2;
        double angleIncrement = 2 * Math.PI / numSides;

        for (int i = 0; i < numSides; i++) {
            double angle = i * angleIncrement;
            int x = (int) (polygon.getX() + sideLength * Math.cos(angle));
            int y = (int) (polygon.getY() + sideLength * Math.sin(angle));
            polyVerticesX[i] = x;
            polyVerticesY[i] = y;
        }

        for (int i = 0; i < numSides; i++) {
            int x3 = polyVerticesX[i];
            int y3 = polyVerticesY[i];
            int x4 = polyVerticesX[(i + 1) % numSides];
            int y4 = polyVerticesY[(i + 1) % numSides];

            if (segmentsIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
                return true;
            }
        }

        return false;
    }

    // Check if two line segments intersect
    private boolean segmentsIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int dx12 = x2 - x1;
        int dy12 = y2 - y1;
        int dx34 = x4 - x3;
        int dy34 = y4 - y3;

        double denominator = dx12 * dy34 - dy12 * dx34;

        if (denominator == 0) {
            return false;
        }

        double t1 =
                ((x1 - x3) * dy34 + (y3 - y1) * dx34) / denominator;
        double t2 =
                ((x3 - x1) * dy12 + (y1 - y3) * dx12) / -denominator;

        return t1 >= 0 && t1 <= 1 && t2 >= 0 && t2 <= 1;
    }

}

