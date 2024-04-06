package Controller;

import Models.Enemy.Enemy;
import Models.Epsilon.Shot;

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

}

