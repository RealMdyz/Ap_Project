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
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(isKeyPressed(KeyEvent.VK_D) && !isKeyPressed(KeyEvent.VK_A))) {
                    x = -1;
                    epsilon.setxVelocity(x * (int)(Constant.getSensitivityForMoves() / 10 - 1));
                }
            }
        });

        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(isKeyPressed(KeyEvent.VK_A) && !isKeyPressed(KeyEvent.VK_D))) {
                    x = 1;
                    epsilon.setxVelocity(x * (int)(Constant.getSensitivityForMoves() / 10 + 1));
                }
            }
        });

        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(isKeyPressed(KeyEvent.VK_S) && !isKeyPressed(KeyEvent.VK_W))) {
                    y = -1;
                    epsilon.setyVelocity(y * (int)(Constant.getSensitivityForMoves() / 10 - 1));
                }
            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(isKeyPressed(KeyEvent.VK_W) && !isKeyPressed(KeyEvent.VK_S))) {
                    y = 1;
                    epsilon.setyVelocity(y * (int)(Constant.getSensitivityForMoves() / 10 + 1));
                }
            }
        });


        actionMap.put("bStore", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constant.setOpenStore(true);
            }
        });
        actionMap.put("ability", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Constant.getPlayerXP() >= 100 && System.currentTimeMillis() - Constant.getAbilityStartTime() > 5 * 60000){
                    //System.out.println(System.currentTimeMillis());
                    Constant.setAbilityStartTime(System.currentTimeMillis());
                    Constant.setPlayerXP(Constant.getPlayerXP() - 100);
                    Constant.setqPressed(true);
                }
                else if(System.currentTimeMillis() - Constant.getAbilityStartTime() > 5 * 60000){
                    JOptionPane.showMessageDialog(gameFrame, "Not enough XP to do your ability!");
                }
                else{
                    JOptionPane.showMessageDialog(gameFrame, "Not enough XP to do your ability!");
                }
            }
        });

        // Key Release Action:
        actionMap.put("moveUpReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y = 0;
                epsilon.setyVelocity(0);
            }
        });

        actionMap.put("moveDownReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y = 0;
                epsilon.setyVelocity(0);

            }
        });

        actionMap.put("moveLeftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = 0;
                epsilon.setxVelocity(0);

            }
        });

        actionMap.put("moveRightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = 0;
                epsilon.setxVelocity(0);

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

    private boolean isKeyPressed(int keyCode) {
        //return false;
       return inputMap.get(KeyStroke.getKeyStroke(keyCode, 0)) != null;
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
