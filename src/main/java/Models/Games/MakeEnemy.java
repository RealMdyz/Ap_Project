package Models.Games;

import Controller.Game.FrameIntersection;
import Models.Constant;
import Models.Enemy.Enemy;
import Models.Enemy.Normal.*;
import Models.Game;
import View.Game.GameFrame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakeEnemy {
    private Game game;
    private List<Class<? extends Enemy>> enemyClasses;

    public MakeEnemy(Game game) {
        this.game = game;
        this.enemyClasses = new ArrayList<>();
        loadEnemyClasses();
    }

    private void loadEnemyClasses() {
        // بارگذاری کلاس‌های دشمن
        enemyClasses.add(Necropick.class);
        enemyClasses.add(Omenoct.class);
        enemyClasses.add(Archmire.class);
        enemyClasses.add(Wyrm.class);
    }

    public Enemy makeRandomEnemy(int currentWaveIndex) {
        Random random = new Random();
        int index = random.nextInt(enemyClasses.size());
        Class<? extends Enemy> enemyClass = enemyClasses.get(index);

        GameFrame gameFrame = game.getEpsilonFrame();
        int locX = random.nextInt(350) + 15;
        int locY = random.nextInt(350) + 15;

        if (enemyClass == Archmire.class) {
            boolean isMini = random.nextBoolean(); // برای Archmire یک boolean تصادفی
            Constructor<? extends Enemy> constructor = null;
            try {
                constructor = enemyClass.getConstructor(int.class, int.class, boolean.class, GameFrame.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                return constructor.newInstance(locX, locY, isMini, gameFrame);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else if (enemyClass == Wyrm.class) {
            // Wyrm نیاز به GameFrame جدید دارد
            GameFrame wyrmFrame = new GameFrame(0, 0, true, false);
            game.getGameFrames().add(wyrmFrame);
            wyrmFrame.setVisible(true);
            wyrmFrame.setBounds(locX, locY, Constant.WIDTH_OF_WYRM, Constant.HEIGHT_OF_WYRM);
            Constructor<? extends Enemy> constructor = null;
            try {
                constructor = enemyClass.getConstructor(int.class, int.class, GameFrame.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                return constructor.newInstance(locX, locY, wyrmFrame);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            // برای Necropick و Omenoct
            Constructor<? extends Enemy> constructor = null;
            try {
                constructor = enemyClass.getConstructor(int.class, int.class, GameFrame.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                return constructor.newInstance(locX, locY, gameFrame);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
