package Server;

import java.util.ArrayList;

public class Squad {
    private String squadName;
    private String ownersName;
    private ArrayList<String> membersName = new ArrayList<>();
    private ArrayList<String> battleHistory = new ArrayList<>();
    private int vaultXP;
    private int monomachiaWins = 0;
    private int xpInBattle = 0;

    private boolean callOfPalioxisActive = false;
    private boolean callOfAdonisActive = false;
    private boolean callOfGefjonActive = false;

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

    public ArrayList<String> getBattleHistory() {
        return battleHistory;
    }

    public void addBattleHistory(String record) {
        this.battleHistory.add(record);
    }

    public int getVaultXP() {
        return vaultXP;
    }

    public void addVaultXP(int xp) {
        this.vaultXP += xp;
    }

    public void subtractVaultXP(int xp) {
        this.vaultXP = Math.max(0, this.vaultXP - xp);
    }

    public boolean activateCallOfPalioxis() {
        int cost = 100 * membersName.size();
        if (vaultXP >= cost) {
            vaultXP -= cost;
            callOfPalioxisActive = true;
            return true;
        }
        return false;
    }

    // Method to activate Call of Adonis
    public boolean activateCallOfAdonis() {
        int cost = 400;
        if (vaultXP >= cost) {
            vaultXP -= cost;
            callOfAdonisActive = true;
            return true;
        }
        return false;
    }

    // Method to activate Call of Gefjon
    public boolean activateCallOfGefjon() {
        int cost = 300;
        if (vaultXP >= cost) {
            vaultXP -= cost;
            callOfGefjonActive = true;
            return true;
        }
        return false;
    }

    public void setBattleHistory(ArrayList<String> battleHistory) {
        this.battleHistory = battleHistory;
    }

    public void setVaultXP(int vaultXP) {
        this.vaultXP = vaultXP;
    }

    public boolean isCallOfPalioxisActive() {
        return callOfPalioxisActive;
    }

    public void setCallOfPalioxisActive(boolean callOfPalioxisActive) {
        this.callOfPalioxisActive = callOfPalioxisActive;
    }

    public boolean isCallOfAdonisActive() {
        return callOfAdonisActive;
    }

    public void setCallOfAdonisActive(boolean callOfAdonisActive) {
        this.callOfAdonisActive = callOfAdonisActive;
    }

    public boolean isCallOfGefjonActive() {
        return callOfGefjonActive;
    }

    public void setCallOfGefjonActive(boolean callOfGefjonActive) {
        this.callOfGefjonActive = callOfGefjonActive;
    }
    public void addXp(int xp) { this.xpInBattle += xp; }
    public void incrementMonomachiaWins() { this.monomachiaWins++; }

    public int getMonomachiaWins() {
        return monomachiaWins;
    }

    public void setMonomachiaWins(int monomachiaWins) {
        this.monomachiaWins = monomachiaWins;
    }

    public int getXpInBattle() {
        return xpInBattle;
    }

    public void setXpInBattle(int xpInBattle) {
        this.xpInBattle = xpInBattle;
    }
}
