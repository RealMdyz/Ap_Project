package Controller.Game;


import Models.Game;
import Models.ObjectsInGame;
import Models.Side;
import View.Game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameIntersection {

    public FrameIntersection(){}
    public static boolean twoFrameIntersection(GameFrame frame1, GameFrame frame2) {
        Rectangle bounds1 = new Rectangle(frame1.getX(), frame1.getY(), frame1.getWidth(), frame1.getHeight());
        Rectangle bounds2 = new Rectangle(frame2.getX(), frame2.getY(), frame2.getWidth(), frame2.getHeight());
        return bounds1.intersects(bounds2);
    }



}