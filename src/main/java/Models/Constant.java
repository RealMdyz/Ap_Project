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


    private final Object lockForStore = new Object();
    public static final int NUMBER_OF_WAVE = 5;
    public static final int RADIUS_FOR_APPEARING_NECROPICK = 200;
    public static final int FIRST_HEIGHT = 700;
    public static final int FIRST_WIDTH = 700;
    public static final int MAX_DISTANCE = 20;
    public static final int SIDE_LENGTH_OF_BARRICADOS = 150;

    public static final int WIDTH_OF_WYRM = 80;
    public static final int HEIGHT_OF_WYRM = 80;
    public static final int SPEED_OF_WYRM = 4;
    public static final int SPEED_OF_NORMAL_ENEMY = 4;
    public static final long TIME_FOR_EACH_COLLECTIBLE = 5000;

    public static final double ALPHA = 0.05;

    private static int level = 50;
    private static int sensitivityForMoves = 50;
    private static long abilityStartTime = -100000;
    private static int playerXP = 0;
    private int minWidthForShrinkage = 450;
    private int minHeightForShrinkage = 450;
    private int reduceForeShrinkage = 3;
    private static boolean isRunning;

    private static int sound = 50;
    private static boolean  rotateVertices = false;

    private static double rotateAfterImpact = 25;
    private Map<String, Integer> keyMap;
    private static boolean openStore = false;
    private static int speedOfShot = 15;
    private static int speedOfImpact = 10;
    private static int widthOfOmenoct = 50;
    private static int heightOfOmenoct = 50;
    private static int speedOfOmenoct = 7;
    private static int endXOmenoct = 0;
    private static int endYOmenoct = 150;
    private static long everyShotOmenoct = 2000;
    private static int widthOfNecropick = 50;
    private static int heightOfNecropick = 50;
    private static int speedOfNecropick = 7;


    private static int widthOfArchmire = 50;
    private static int heightOfArchmire = 50;
    private static int widthOfMiniArchmire = 30;
    private static int heightOfMiniArchmire = 30;
    private static long startOFHypnosSlumber = 0;

    private static int speedOfArchmire = 4;
    private static int savedXp;

    private int upKey, downKey, leftKey, rightKey, abilityKey, storeKey;

    private static boolean qPressed = false;
    private boolean bossTriggered = false;


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
        int levelOfAttack, levelOfDefend, levelOfChangeShape;
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
            levelOfAttack = (scanner.nextInt());
            levelOfDefend = (scanner.nextInt());
            levelOfChangeShape = scanner.nextInt();

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
        int levelOfAttack, levelOfDefend, levelOfChangeShape;

        try {
            scanner = new Scanner(file);
            savedXp = scanner.nextInt();
            levelOfAttack = (scanner.nextInt());
            levelOfDefend = (scanner.nextInt());
            levelOfChangeShape = scanner.nextInt();

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

    public static long getEveryShotOmenoct() {
        return everyShotOmenoct;
    }

    public static void setEveryShotOmenoct(long everyShotOmenoct) {
        Constant.everyShotOmenoct = everyShotOmenoct;
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

    public static double getRotateAfterImpact() {
        return rotateAfterImpact;
    }

    public static void setRotateAfterImpact(double rotateAfterImpact) {
        Constant.rotateAfterImpact = rotateAfterImpact;
    }

    public boolean isBossTriggered() {
        return bossTriggered;
    }

    public void setBossTriggered(boolean bossTriggered) {
        this.bossTriggered = bossTriggered;
    }

    public int getMinWidthForShrinkage() {
        return minWidthForShrinkage;
    }

    public void setMinWidthForShrinkage(int minWidthForShrinkage) {
        this.minWidthForShrinkage = minWidthForShrinkage;
    }

    public int getMinHeightForShrinkage() {
        return minHeightForShrinkage;
    }

    public void setMinHeightForShrinkage(int minHeightForShrinkage) {
        this.minHeightForShrinkage = minHeightForShrinkage;
    }

    public Object getLockForStore() {
        return lockForStore;
    }

    public int getReduceForeShrinkage() {
        return reduceForeShrinkage;
    }

    public void setReduceForeShrinkage(int reduceForeShrinkage) {
        this.reduceForeShrinkage = reduceForeShrinkage;
    }

    public static int getWidthOfOmenoct() {
        return widthOfOmenoct;
    }

    public static void setWidthOfOmenoct(int widthOfOmenoct) {
        Constant.widthOfOmenoct = widthOfOmenoct;
    }

    public static int getHeightOfOmenoct() {
        return heightOfOmenoct;
    }

    public static void setHeightOfOmenoct(int heightOfOmenoct) {
        Constant.heightOfOmenoct = heightOfOmenoct;
    }

    public static int getSpeedOfOmenoct() {
        return speedOfOmenoct;
    }

    public static void setSpeedOfOmenoct(int speedOfOmenoct) {
        Constant.speedOfOmenoct = speedOfOmenoct;
    }

    public static int getEndXOmenoct() {
        return endXOmenoct;
    }

    public static void setEndXOmenoct(int endXOmenoct) {
        Constant.endXOmenoct = endXOmenoct;
    }

    public static int getEndYOmenoct() {
        return endYOmenoct;
    }

    public static void setEndYOmenoct(int endYOmenoct) {
        Constant.endYOmenoct = endYOmenoct;
    }

    public static int getWidthOfNecropick() {
        return widthOfNecropick;
    }

    public static void setWidthOfNecropick(int widthOfNecropick) {
        Constant.widthOfNecropick = widthOfNecropick;
    }

    public static int getHeightOfNecropick() {
        return heightOfNecropick;
    }

    public static void setHeightOfNecropick(int heightOfNecropick) {
        Constant.heightOfNecropick = heightOfNecropick;
    }

    public static int getSpeedOfNecropick() {
        return speedOfNecropick;
    }

    public static void setSpeedOfNecropick(int speedOfNecropick) {
        Constant.speedOfNecropick = speedOfNecropick;
    }

    public static int getWidthOfArchmire() {
        return widthOfArchmire;
    }

    public static void setWidthOfArchmire(int widthOfArchmire) {
        Constant.widthOfArchmire = widthOfArchmire;
    }

    public static int getHeightOfArchmire() {
        return heightOfArchmire;
    }

    public static void setHeightOfArchmire(int heightOfArchmire) {
        Constant.heightOfArchmire = heightOfArchmire;
    }

    public static int getSpeedOfArchmire() {
        return speedOfArchmire;
    }

    public static void setSpeedOfArchmire(int speedOfArchmire) {
        Constant.speedOfArchmire = speedOfArchmire;
    }

    public static int getWidthOfMiniArchmire() {
        return widthOfMiniArchmire;
    }

    public static void setWidthOfMiniArchmire(int widthOfMiniArchmire) {
        Constant.widthOfMiniArchmire = widthOfMiniArchmire;
    }

    public static int getHeightOfMiniArchmire() {
        return heightOfMiniArchmire;
    }

    public static void setHeightOfMiniArchmire(int heightOfMiniArchmire) {
        Constant.heightOfMiniArchmire = heightOfMiniArchmire;
    }

    public static long getStartOFHypnosSlumber() {
        return startOFHypnosSlumber;
    }

    public static void setStartOFHypnosSlumber(long startOFHypnosSlumber) {
        Constant.startOFHypnosSlumber = startOFHypnosSlumber;
    }
}
