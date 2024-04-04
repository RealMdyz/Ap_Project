package Models;

public class Constant {

    public static final int FIRST_HEIGHT = 700;
    public static final int FIRST_WIDTH = 700;

    private static int level = 0;
    private static int sensitivityForMoves = 0;
    private static long abilityStartTime = -100000;
    private  int playerXP = 0;
    private static boolean isRunning;
    private static int sound = 0;
    private static char keyForMoveUp = 'W';
    private static char keyForMoveDown = 'S';
    private static char keyForMoveLeft = 'A';
    private static char keyForMoveRight = 'D';
    private static boolean openStore = false;
    private static int speedOfShot = 15;
    private static int widthOfTrighrath = 20;
    private static int heightOfTrighrath = 20;
    private static int widthOfSquarantine = 20;
    private static int heightOfSquarantine = 20;


    public Constant(){

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

    public int getPlayerXP() {
        return playerXP;
    }

    public void setPlayerXP(int playerXP) {
        this.playerXP = playerXP;
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
}
