package Server;

import java.util.ArrayList;

public class Squad {
    private String squadName;
    private String ownersName;
    private ArrayList<String> membersName = new ArrayList<>();

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public ArrayList<String> getMembersName() {
        return membersName;
    }

    public void setMembersName(ArrayList<String> membersName) {
        this.membersName = membersName;
    }
}
