package Models;

import Models.Epsilon.Epsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Arrays;
import java.util.List;

public class Constant {

    public static final int FIRST_HEIGHT = 700;
    public static final int FIRST_WIDTH = 700;
    public static final int MAX_DISTANCE = 20;
    public static final double ALPHA = 0.05;

    private static int level = 10;
    private static int sensitivityForMoves = 0;
    private static long abilityStartTime = -100000;
    private static int playerXP = 0;
    private static boolean isRunning;
    private static int sound = 10;
    private static boolean  rotateVertices = false;

    private Map<String, Integer> keyMap;
    private static boolean openStore = false;
    private static int speedOfShot = 15;
    private static int widthOfTrighrath = 50;
    private static int heightOfTrighrath = 50;
    private static int speedOfTrighrath = 7;
    private static int widthOfSquarantine = 50;
    private static int heightOfSquarantine = 50;
    private static int speedOfSquarantine  = 5;
    private static int speedOfImpact = 10;
    private static int savedXp;

    private int upKey, downKey, leftKey, rightKey, abilityKey, storeKey;

    private static boolean qPressed = false;
    public Constant(){
        savedXp = 0;

        keyMap = new HashMap<>();
        keyMap.put("Left", KeyEvent.VK_A);
        leftKey = keyMap.get("Left");
        keyMap.put("Right", KeyEvent.VK_D);
        rightKey = keyMap.get("Right");
        keyMap.put("Up", KeyEvent.VK_W);
        upKey = keyMap.get("Up");
        keyMap.put("Down", KeyEvent.VK_S);
        downKey = keyMap.get("Down");
        keyMap.put("OpenShop", KeyEvent.VK_B);
        storeKey = keyMap.get("OpenShop");
        keyMap.put("ActivateAbility", KeyEvent.VK_Q);
        abilityKey = keyMap.get("ActivateAbility");

        File file = new File("gameData");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // FirstLine: SavedXp;
            // SecondLine : LevelWritOfAres;
            // ThirdLine : LevelOfWritOfProteus;
            // FourthLine : writeOfAceso

        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            savedXp = scanner.nextInt();
            Epsilon.setLevelOfWritOfAres(scanner.nextInt());
            Epsilon.setLevelOfWritOfProteus(scanner.nextInt());
            int l = scanner.nextInt();
            if(l == 0)
                Epsilon.setWriteOfAceso(false);
            else
                Epsilon.setWriteOfAceso(true);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
       // System.out.println(savedXp + " " + Epsilon.getLevelOfWritOfAres() + " " + Epsilon.getLevelOfWritOfProteus() + " " + Epsilon.isWriteOfAceso());


    }
    public void updateToSkillAndXp(){
        File file = new File("gameData");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // FirstLine: SavedXp;
            // SecondLine : LevelWritOfAres;
            // ThirdLine : LevelOfWritOfProteus;
            // FourthLine : writeOfAceso

        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            savedXp = scanner.nextInt();
            Epsilon.setLevelOfWritOfAres(scanner.nextInt());
            Epsilon.setLevelOfWritOfProteus(scanner.nextInt());
            int l = scanner.nextInt();
            if(l == 0)
                Epsilon.setWriteOfAceso(false);
            else
                Epsilon.setWriteOfAceso(true);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeInFile(int xpForSave, int levelAres,  int levelAceso, int levelProteus){
        File file = new File("gameData");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // FirstLine: SavedXp;
            // SecondLine : LevelWritOfAres;
            // ThirdLine : LevelOfWritOfProteus;
            // FourthLine : writeOfAceso

        }
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println(xpForSave);
            printWriter.println(levelAres);
            printWriter.println(levelProteus);
            printWriter.println(levelAceso);
            printWriter.flush();
            printWriter.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public void setKeyBindings(Map<String, Integer> keyBindings) {
        this.keyMap = keyBindings;
    }

    public void updateKeyBindings() {
        // Update key bindings according to your requirements
        // For example:
        this.upKey = keyMap.get("Up");
        this.downKey = keyMap.get("Down");
        this.leftKey = keyMap.get("Left");
        this.rightKey = keyMap.get("Right");
        this.storeKey = keyMap.get("OpenShop");
        this.abilityKey = keyMap.get("ActivateAbility");
    }


    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Constant.level = level;
    }

    public static int getSensitivityForMoves() {
        return sensitivityForMoves;
    }

    public static void setSensitivityForMoves(int sensitivityForMoves) {
        Constant.sensitivityForMoves = sensitivityForMoves;
    }

    public static int getSound() {
        return sound;
    }

    public static void setSound(int sound) {
        Constant.sound = sound;
    }

    public static int getPlayerXP() {
        return playerXP;
    }

    public static void setPlayerXP(int playerXP) {
        Constant.playerXP = playerXP;
    }

    public static boolean isOpenStore() {
        return openStore;
    }

    public static void setOpenStore(boolean openStore) {
        Constant.openStore = openStore;
    }

    public static long getAbilityStartTime() {
        return abilityStartTime;
    }

    public static void setAbilityStartTime(long abilityStartTime) {
        Constant.abilityStartTime = abilityStartTime;
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        Constant.isRunning = isRunning;
    }

    public static int getSpeedOfShot() {
        return speedOfShot;
    }

    public static void setSpeedOfShot(int speedOfShot) {
        Constant.speedOfShot = speedOfShot;
    }

    public static int getWidthOfTrighrath() {
        return widthOfTrighrath;
    }

    public static void setWidthOfTrighrath(int widthOfTrighrath) {
        Constant.widthOfTrighrath = widthOfTrighrath;
    }

    public static int getHeightOfTrighrath() {
        return heightOfTrighrath;
    }

    public static void setHeightOfTrighrath(int heightOfTrighrath) {
        Constant.heightOfTrighrath = heightOfTrighrath;
    }

    public static int getWidthOfSquarantine() {
        return widthOfSquarantine;
    }

    public static void setWidthOfSquarantine(int widthOfSquarantine) {
        Constant.widthOfSquarantine = widthOfSquarantine;
    }

    public static int getHeightOfSquarantine() {
        return heightOfSquarantine;
    }

    public static void setHeightOfSquarantine(int heightOfSquarantine) {
        Constant.heightOfSquarantine = heightOfSquarantine;
    }

    public static int getSpeedOfTrighrath() {
        return speedOfTrighrath;
    }

    public static void setSpeedOfTrighrath(int speedOfTrighrath) {
        Constant.speedOfTrighrath = speedOfTrighrath;
    }

    public static int getSpeedOfSquarantine() {
        return speedOfSquarantine;
    }

    public static void setSpeedOfSquarantine(int speedOfSquarantine) {
        Constant.speedOfSquarantine = speedOfSquarantine;
    }

    public static int getSpeedOfImpact() {
        return speedOfImpact;
    }

    public static void setSpeedOfImpact(int speedOfImpact) {
        Constant.speedOfImpact = speedOfImpact;
    }

    public static int getSavedXp() {
        return savedXp;
    }

    public static void setSavedXp(int savedXp) {
        Constant.savedXp = savedXp;
    }

    public static boolean isqPressed() {
        return qPressed;
    }

    public static void setqPressed(boolean qPressed) {
        Constant.qPressed = qPressed;
    }

    public Map<String, Integer> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, Integer> keyMap) {
        this.keyMap = keyMap;
    }

    public int getUpKey() {
        return upKey;
    }

    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    public int getAbilityKey() {
        return abilityKey;
    }

    public void setAbilityKey(int abilityKey) {
        this.abilityKey = abilityKey;
    }

    public int getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(int storeKey) {
        this.storeKey = storeKey;
    }

    public static boolean isRotateVertices() {
        return rotateVertices;
    }

    public static void setRotateVertices(boolean rotateVertices) {
        Constant.rotateVertices = rotateVertices;
    }
}
