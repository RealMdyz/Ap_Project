package Models.Enemy.MiniBos.BlackOrb;

import Models.ObjectsInGame;

import java.awt.*;

public class Line {
    Point p1, p2;
    private long lastAttack = 0;

    Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }


}