package View.Game;

import Models.Constant;
import Models.Epsilon.Epsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InputListener {
    InputMap inputMap;
    ActionMap actionMap;
    GameFrame gameFrame;
    Epsilon epsilon;
    Constant constant;

    private int xMousePress = -1, yMousePress = -1;

    int x = 0;
    int y = 0;
    public InputListener(GameFrame gameFrame, Constant constant) {
        this.gameFrame = gameFrame;
        this.constant = constant;
        epsilon = gameFrame.getEpsilon();
        createKeyBindings();
        createKeyActions();

        createMouseAction();
        System.out.println("Added");

    }
    private void createKeyBindings() {

        inputMap = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameFrame.getRootPane().getActionMap();

        // Key Press:
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "bStore");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "ability");
        // Key Release:
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "moveUpReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "moveDownReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "moveLeftReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "moveRightReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0, true), "bStoreReleased");

    }
    private void createKeyActions() {

        // Key Press Action:
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y = -1;
                epsilon.setyVelocity(y * (int)(Constant.getSensitivityForMoves() / 10 + 1) );
            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y += 1;
                epsilon.setyVelocity(y * (int)(Constant.getSensitivityForMoves() / 10 + 1));
            }
        });

        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x =  -1;
                epsilon.setxVelocity(x * (int)(Constant.getSensitivityForMoves() / 10 + 1));
            }
        });

        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = 1;
                epsilon.setxVelocity(x * (int)(Constant.getSensitivityForMoves() / 10 + 1));
            }
        });
        actionMap.put("bStore", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constant.setOpenStore(true);
                System.out.println("Hello");
            }
        });
        actionMap.put("ability", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(System.currentTimeMillis());
                Constant.setAbilityStartTime(System.currentTimeMillis());
            }
        });

        // Key Release Action:
        actionMap.put("moveUpReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y = 1;
                epsilon.setyVelocity(0);
            }
        });

        actionMap.put("moveDownReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setyVelocity(0);
                y = -1;
            }
        });

        actionMap.put("moveLeftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setxVelocity(0);
                x = 0;
            }
        });

        actionMap.put("moveRightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setxVelocity(0);
                x = 0;
            }
        });
        actionMap.put("bStoreReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
    private void createMouseAction(){
        gameFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Point mousePoint = evt.getPoint();
                xMousePress = mousePoint.x;
                yMousePress = mousePoint.y;
            }
        });
    }

    public int getyMousePress() {
        return yMousePress;
    }

    public void setyMousePress(int yMousePress) {
        this.yMousePress = yMousePress;
    }

    public int getxMousePress() {
        return xMousePress;
    }

    public void setxMousePress(int xMousePress) {
        this.xMousePress = xMousePress;
    }


}
