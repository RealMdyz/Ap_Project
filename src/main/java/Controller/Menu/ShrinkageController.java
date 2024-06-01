package Controller.Menu;

import javax.swing.*;
import java.awt.*;

public class ShrinkageController {

    int limitForWidth;
    int limitForHeight;
    int reduced;
    public ShrinkageController(int limitForHeight, int limitForWidth, int reduced){
        this.limitForHeight = limitForHeight;
        this.limitForWidth = limitForWidth;
        this.reduced = reduced;
    }

    public boolean panelReducedSize(JFrame jFrame){
        if (jFrame.getHeight() > limitForHeight && jFrame.getWidth() > limitForWidth) {
            int newWidth = jFrame.getWidth() - reduced;
            int newHeight = jFrame.getHeight() - reduced;

            jFrame.setBounds(jFrame.getX() + reduced / 2, jFrame.getY() + reduced / 2, newWidth, newHeight);
            return true;
        }
        return false;
    }
}
