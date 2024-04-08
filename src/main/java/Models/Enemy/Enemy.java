package Models.Enemy;

import Models.*;

public class Enemy extends ObjectsInGame implements LocalRouting, Aggression, Moveable {

    public Enemy(int x, int y, int hp) {
        super(x, y, hp);
    }

    @Override
    public void aggression() {

    }

    @Override
    public void localRouting(int xEpsilon, int yEpsilon) {

    }

    @Override
    public void move() {

    }
    public void doImpact(int xImpact, int yImpact){
        int xChar = getX(); // Your character's x position
        int yChar = getY(); // Your character's y position

        // Calculate the direction vector from character to impact point
        double dx = xImpact - xChar;
        double dy = yImpact - yChar;

        // Calculate the distance from character to impact point
        double distance = Math.sqrt(dx * dx + dy * dy);
        double angle = Math.atan2(dy, dx);

        // Adjust speed as needed
        double speed = Constant.getSpeedOfImpact();
        System.out.println(xImpact + " " + yImpact);
        // Calculate the velocity components based on the distance
        double vx = speed * Math.cos(angle) ;
        double vy = speed * Math.sin(angle) ;
        this.setX(this.getX() + (int)(vx));
        this.setY(this.getY() + (int)(vy));
    }
}
