package View.Game;

import Models.Constant;
import Models.Epsilon.Epsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class InputListener {
    InputMap inputMap;
    ActionMap actionMap;
    GameFrame gameFrame;
    Epsilon epsilon;
    Constant constant;

    private int xMousePress = -1, yMousePress = -1;

    int x = 0;
    int y = 0;
    private final Set<String> pressedKeys = new HashSet<>();
    public InputListener(GameFrame gameFrame, Constant constant, Epsilon epsilon) {
        this.gameFrame = gameFrame;
        this.constant = constant;
        this.epsilon = epsilon;
        createKeyBindings();
        createKeyActions();

        createMouseAction();


    }
    private void createKeyBindings() {

        inputMap = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameFrame.getRootPane().getActionMap();

        // Key Press:

        // Key Press:
        inputMap.put(KeyStroke.getKeyStroke(constant.getUpKey(), 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(constant.getDownKey(), 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(constant.getLeftKey(), 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(constant.getRightKey(), 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(constant.getStoreKey(), 0), "bStore");
        inputMap.put(KeyStroke.getKeyStroke(constant.getAbilityKey(), 0), "ability");
        System.out.println(constant.getLeftKey());
        // Key Release:
        inputMap.put(KeyStroke.getKeyStroke(constant.getUpKey(), 0, true), "moveUpReleased");
        inputMap.put(KeyStroke.getKeyStroke(constant.getDownKey(), 0, true), "moveDownReleased");
        inputMap.put(KeyStroke.getKeyStroke(constant.getLeftKey(), 0, true), "moveLeftReleased");
        inputMap.put(KeyStroke.getKeyStroke(constant.getRightKey(), 0, true), "moveRightReleased");
        inputMap.put(KeyStroke.getKeyStroke(constant.getStoreKey(), 0, true), "bStoreReleased");

    }
    private void createKeyActions() {

        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add("up");
                updateVelocity();
            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add("down");
                updateVelocity();
            }
        });

        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add("left");
                updateVelocity();
            }
        });

        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.add("right");
                updateVelocity();
            }
        });

        // Key Release Action
        actionMap.put("moveUpReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove("up");
                updateVelocity();
            }
        });

        actionMap.put("moveDownReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove("down");
                updateVelocity();
            }
        });

        actionMap.put("moveLeftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove("left");
                updateVelocity();
            }
        });

        actionMap.put("moveRightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedKeys.remove("right");
                updateVelocity();
            }
        });


        actionMap.put("bStore", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(System.currentTimeMillis() - Constant.getStartOFHypnosSlumber() > 10000)
                     Constant.setOpenStore(true);
            }
        });
        actionMap.put("ability", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Constant.getPlayerXP() >= Constant.ABILITY_XP && System.currentTimeMillis() - Constant.getAbilityStartTime() > 5 * 60000){
                    //System.out.println(System.currentTimeMillis());
                    Constant.setAbilityStartTime(System.currentTimeMillis());
                    Constant.setPlayerXP(Constant.getPlayerXP() - Constant.ABILITY_XP);
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
//        actionMap.put("moveUpReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                y = 0;
//                epsilon.setyVelocity(0);
//            }
//        });
//
//        actionMap.put("moveDownReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                y = 0;
//                epsilon.setyVelocity(0);
//
//            }
//        });
//
//        actionMap.put("moveLeftReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                x = 0;
//                epsilon.setxVelocity(0);
//
//            }
//        });
//
//        actionMap.put("moveRightReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                x = 0;
//                epsilon.setxVelocity(0);
//
//            }
//        });
        actionMap.put("bStoreReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
    private void updateVelocity() {
        // Check if opposing keys are pressed
        if ((pressedKeys.contains("up") && pressedKeys.contains("down"))) {
            // If opposing keys are pressed, stop the character
            epsilon.setyVelocity(0);
        }
        else if ((pressedKeys.contains("left") && pressedKeys.contains("right"))) {
            // If opposing keys are pressed, stop the character
            epsilon.setxVelocity(0);

        }
        else {
            // Calculate x and y velocity based on pressed keys
            int xVelocity = 0;
            int yVelocity = 0;

            if (pressedKeys.contains("left")) {
                xVelocity = -1;
            } else if (pressedKeys.contains("right")) {
                xVelocity = 1;
            }

            if (pressedKeys.contains("up")) {
                yVelocity = -1;
            } else if (pressedKeys.contains("down")) {
                yVelocity = 1;
            }
            epsilon.setxVelocity(xVelocity * (int)(Constant.getSensitivityForMoves() / 10) + xVelocity);
            epsilon.setyVelocity(yVelocity * (int)(Constant.getSensitivityForMoves() / 10) + yVelocity);
        }
    }
    private void createMouseAction(){
        gameFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Point mousePoint = evt.getPoint();
                xMousePress = mousePoint.x;
                yMousePress = mousePoint.y;
            }
        });
        gameFrame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Point mousePoint = evt.getPoint();
                int mouseX = mousePoint.x;
                int mouseY = mousePoint.y;

                int epsilonCenterX = epsilon.getX() + epsilon.getWidth() / 2 + 7;
                int epsilonCenterY = epsilon.getY() + epsilon.getHeight() / 2 - 7;

                double angle = Math.atan2(mouseY - epsilonCenterY, mouseX - epsilonCenterX);

                double angleDegrees = Math.toDegrees(angle);

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
