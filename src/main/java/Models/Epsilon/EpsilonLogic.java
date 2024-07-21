package Models.Epsilon;

import Models.Constant;
import Models.Game;

import java.util.ArrayList;

public class EpsilonLogic {

    private ArrayList<Vertex> verticesForCerberus = new ArrayList<>();
    private ArrayList<Vertex>  verticesForProteus = new ArrayList<>();
    private long startOfNonHoveringDistance = 0 ;
    private boolean isInPhonoiSlaughter = false;
    private long lastWritOfAceso = 0;
    private long lastWritOfAstrape = 0;
    private long lastWritOfCerberus = 0;
    private long lastWriteOfProteus = 0;
    public EpsilonLogic(){

    }
    protected void move(Epsilon epsilon){

        int xLimit = epsilon.getCurrentFrame().getWidth();
        int yLimit = epsilon.getCurrentFrame().getHeight();

        double t = ((double)System.currentTimeMillis() - (double)epsilon.getImpactTime()) / 1000;
        t = Math.max(0, (1 - t));
        double x = (epsilon.getxVelocityImpact()) * (t);
        double y = ((epsilon.getyVelocityImpact())) * (t);

        epsilon.setxVelocity(epsilon.getxVelocity() + (int)x);
        epsilon.setyVelocity(epsilon.getyVelocity() + (int)y);

        if(epsilon.getX() <= 0  && epsilon.getxVelocity() > 0)
            addX(epsilon.getxVelocity(), epsilon);
        else if(epsilon.getX() >= xLimit - epsilon.getWidth() - 10 && epsilon.getxVelocity() < 0)
            addX(epsilon.getxVelocity(), epsilon);
        else if(epsilon.getX() >= 0 && epsilon.getX() <= xLimit - epsilon.getWidth() - 10)
            addX(epsilon.getxVelocity(), epsilon);
        if(epsilon.getY() <= 0  && epsilon.getyVelocity() > 0)
            addY(epsilon.getyVelocity(), epsilon);
        else if(epsilon.getY() >= yLimit - epsilon.getHeight() && epsilon.getyVelocity() < 0)
            addY(epsilon.getyVelocity(), epsilon);
        else if(epsilon.getY() >= 0 && epsilon.getY() <= yLimit - epsilon.getHeight())
            addY(epsilon.getyVelocity(), epsilon);
        //System.out.println(x + " " + y);
        epsilon.setxVelocity(epsilon.getxVelocity() - (int)x);
        epsilon.setyVelocity(epsilon.getyVelocity() - (int)y);

        epsilon.getCurrentFrame().repaint();
    }
    private void addX(int amount, Epsilon epsilon){
        epsilon.setX(epsilon.getX() + amount);
        for(Vertex vertex : verticesForCerberus){
            vertex.setX(vertex.getX() + amount);
        }
        for(Vertex vertex : verticesForProteus){
            vertex.setX(vertex.getX() + amount);
        }
    }
    private void addY(int amount, Epsilon epsilon){
        epsilon.setY(epsilon.getY() + amount);
        for(Vertex vertex : verticesForCerberus){
            vertex.setY(vertex.getY() + amount);
        }
        for(Vertex vertex : verticesForProteus){
            vertex.setY(vertex.getY() + amount);
        }
    }

    public boolean isInDeimosDismay(){
        return System.currentTimeMillis() - startOfNonHoveringDistance < 10000;
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

    public long getLastWritOfAceso() {
        return lastWritOfAceso;
    }
    public boolean isWritOfAstrapeAvailable(){
        if(System.currentTimeMillis() - lastWritOfAstrape > 500){
            return true;
        }
        return false;
    }

    public boolean isWritOfCerberusAvailable(){
        if(System.currentTimeMillis() - lastWritOfCerberus > 15000){
            return true;
        }
        return false;
    }
    public boolean isWritOfProteusAvailable(){
        if(System.currentTimeMillis() - lastWriteOfProteus > 500){
            return true;
        }
        return false;
    }
    public void setLastWritOfAceso(long lastWritOfAceso) {
        this.lastWritOfAceso = lastWritOfAceso;
    }

    public ArrayList<Vertex> getVerticesForCerberus() {
        return verticesForCerberus;
    }

    public void setVerticesForCerberus(ArrayList<Vertex> verticesForCerberus) {
        this.verticesForCerberus = verticesForCerberus;
    }

    public ArrayList<Vertex> getVerticesForProteus() {
        return verticesForProteus;
    }

    public void setVerticesForProteus(ArrayList<Vertex> verticesForProteus) {
        this.verticesForProteus = verticesForProteus;
    }

    public long getLastWritOfAstrape() {
        return lastWritOfAstrape;
    }

    public void setLastWritOfAstrape(long lastWritOfAstrape) {
        this.lastWritOfAstrape = lastWritOfAstrape;
    }

    public long getLastWritOfCerberus() {
        return lastWritOfCerberus;
    }

    public void setLastWritOfCerberus(long lastWritOfCerberus) {
        this.lastWritOfCerberus = lastWritOfCerberus;
    }

    public long getLastWriteOfProteus() {
        return lastWriteOfProteus;
    }

    public void setLastWriteOfProteus(long lastWriteOfProteus) {
        this.lastWriteOfProteus = lastWriteOfProteus;
    }
}
