package Server;

public class History {
    String name;
    long mostAliveTime;
    int mostGainedXp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMostAliveTime() {
        return mostAliveTime;
    }

    public void setMostAliveTime(long mostAliveTime) {
        this.mostAliveTime = mostAliveTime;
    }

    public int getMostGainedXp() {
        return mostGainedXp;
    }

    public void setMostGainedXp(int mostGainedXp) {
        this.mostGainedXp = mostGainedXp;
    }
}
