package Models.Enemy;

import Models.Aggression;
import Models.LocalRouting;
import Models.Moveable;
import Models.ObjectsInGame;

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
}
