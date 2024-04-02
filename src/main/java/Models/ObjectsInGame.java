package Models;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ObjectsInGame extends JLabel {

    private int x;
    private int y;
    private int width;
    private int height;

    protected BufferedImage background;

    public ObjectsInGame(int x, int y) {

        this.x = x;
        this.y = y;

    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setBackground(BufferedImage background) {
        this.background = background;
    }

}