package Models.Epsilon;

import Models.Constant;
import Models.Game;

import java.util.ArrayList;

public class EpsilonLogic {

    private ArrayList<Vertex> vertices = new ArrayList<>();
    private long startOfNonHoveringDistance = 0 ;
    private boolean isInPhonoiSlaughter = false;
    public EpsilonLogic(){

    }
    protected void fireShot(Game game){
        if (game.getInputListener().getxMousePress() != -1 || game.getInputListener().getyMousePress() != -1) {
            Shot shot = new Shot(game.getEpsilon().getX(), game.getEpsilon().getY(), game.getEpsilon().getCurrentFrame(), true);
            if(isInPhonoiSlaughter)
                shot.setPower(50);
            isInPhonoiSlaughter = false;
            game.getEpsilon().getCurrentFrame().add(shot);
            game.getEpsilon().shots.add(shot);
            shot.setV(game.getInputListener().getxMousePress(), game.getInputListener().getyMousePress());
            game.getInputListener().setxMousePress(-1);
            game.getInputListener().setyMousePress(-1);
        }
    }
    protected void move(Epsilon epsilon){

        int xLimit = epsilon.getCurrentFrame().getWidth();
        int yLimit = epsilon.getCurrentFrame().getHeight();

        // System.out.println(xLimit + " " + yLimit);
        //System.out.println(this.getX() + " " + this.getY() + " " + this.getxVelocity() + " " + this.getyVelocity());
        double t = ((double)System.currentTimeMillis() - (double)epsilon.getImpactTime()) / 1000;
        t = Math.max(0, (1 - t));
        double x = (epsilon.getxVelocityImpact()) * (t);
        double y = ((epsilon.getyVelocityImpact())) * (t);

        epsilon.setxVelocity(epsilon.getxVelocity() + (int)x);
        epsilon.setyVelocity(epsilon.getyVelocity() + (int)y);

        for(Vertex vertex : vertices){
            vertex.setxVelocity(epsilon.getxVelocity());
            vertex.setyVelocity(epsilon.getyVelocity());
            vertex.move(xLimit, yLimit);
        }

        if(epsilon.getX() <= 0  && epsilon.getxVelocity() > 0)
            epsilon.setX(epsilon.getX() + epsilon.getxVelocity());
        else if(epsilon.getX() >= xLimit - epsilon.getWidth() - 10 && epsilon.getxVelocity() < 0)
            epsilon.setX(epsilon.getX() + epsilon.getxVelocity());
        else if(epsilon.getX() >= 0 && epsilon.getX() <= xLimit - epsilon.getWidth() - 10)
            epsilon.setX(epsilon.getX() + epsilon.getxVelocity());
        if(epsilon.getY() <= 0  && epsilon.getyVelocity() > 0)
            epsilon.setY(epsilon.getY() + epsilon.getyVelocity());
        else if(epsilon.getY() >= yLimit - epsilon.getHeight() && epsilon.getyVelocity() < 0)
            epsilon.setY(epsilon.getY() + epsilon.getyVelocity());
        else if(epsilon.getY() >= 0 && epsilon.getY() <= yLimit - epsilon.getHeight())
            epsilon.setY(epsilon.getY() + epsilon.getyVelocity());
        //System.out.println(x + " " + y);
        epsilon.setxVelocity(epsilon.getxVelocity() - (int)x);
        epsilon.setyVelocity(epsilon.getyVelocity() - (int)y);

        epsilon.getCurrentFrame().repaint();
    }
    public void setPosVertex(){

    }

    public boolean isInPhonoiSlaughter() {
        return isInPhonoiSlaughter;
    }

    public void setInPhonoiSlaughter(boolean inPhonoiSlaughter) {
        isInPhonoiSlaughter = inPhonoiSlaughter;
    }

    public long getStartOfNonHoveringDistance() {
        return startOfNonHoveringDistance;
    }

    public void setStartOfNonHoveringDistance(long startOfNonHoveringDistance) {
        this.startOfNonHoveringDistance = startOfNonHoveringDistance;
    }
}
