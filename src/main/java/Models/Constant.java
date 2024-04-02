package Models;

public class Constant {

    public static final int FIRST_HEIGHT = 700;
    public static final int FIRST_WIDTH = 700;

    private static int level = 0;
    private static int sensitivityForMoves = 0;

    private static int sound = 0;
    private static char keyForMoveUp = 'W';
    private static char keyForMoveDown = 'S';
    private static char keyForMoveLeft = 'A';
    private static char keyForMoveRight = 'D';
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
}
