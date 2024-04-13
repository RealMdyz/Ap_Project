package Models;

import Models.Epsilon.Epsilon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Constant {

    public static final int FIRST_HEIGHT = 700;
    public static final int FIRST_WIDTH = 700;
    public static final int MAX_DISTANCE = 100;

    private static int level = 10;
    private static int sensitivityForMoves = 40;
    private static long abilityStartTime = -100000;
    private static int playerXP = 0;
    private static boolean isRunning;
    private static int sound = 10;
    private static char keyForMoveUp = 'W';
    private static char keyForMoveDown = 'S';
    private static char keyForMoveLeft = 'A';
    private static char keyForMoveRight = 'D';
    private static boolean openStore = false;
    private static int speedOfShot = 15;
    private static int widthOfTrighrath = 50;
    private static int heightOfTrighrath = 50;
    private static int speedOfTrighrath = 5;
    private static int widthOfSquarantine = 50;
    private static int heightOfSquarantine = 50;
    private static int speedOfSquarantine  = 5;
    private static int speedOfImpact = 10;
    private static int savedXp;

    public Constant(){
        savedXp = 0;
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

    public static char getKeyForMoveUp() {
        return keyForMoveUp;
    }

    public static void setKeyForMoveUp(char keyForMoveUp) {
        Constant.keyForMoveUp = keyForMoveUp;
    }

    public static char getKeyForMoveLeft() {
        return keyForMoveLeft;
    }

    public static void setKeyForMoveLeft(char keyForMoveLeft) {
        Constant.keyForMoveLeft = keyForMoveLeft;
    }

    public static char getKeyForMoveDown() {
        return keyForMoveDown;
    }

    public static void setKeyForMoveDown(char keyForMoveDown) {
        Constant.keyForMoveDown = keyForMoveDown;
    }

    public static char getKeyForMoveRight() {
        return keyForMoveRight;
    }

    public static void setKeyForMoveRight(char keyForMoveRight) {
        Constant.keyForMoveRight = keyForMoveRight;
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
}
