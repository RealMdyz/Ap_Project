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
    public  void changeLocalFrame(GameFrame frame, ObjectsInGame entity) {

    }
    public static ArrayList<Side> twoFrameOverlapSide(GameFrame frame1, GameFrame frame2){
        ArrayList<Side> sides = new ArrayList<>();
        if(frame2.getX()+frame2.getWidth()<=frame1.getX())  sides.add(Side.LEFT);
        if(frame2.getX()<=frame1.getX()+frame1.getWidth() ) sides.add(Side.RIGHT);
        if(frame2.getY()+frame2.getHeight()>=frame1.getY()) sides.add(Side.UP);
        if(frame2.getY()<=frame1.getY()+frame1.getHeight() ) sides.add(Side.DOWN);
        return sides;
    }



}