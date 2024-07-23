package MyProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyProjectData {

    private static MyProjectData projectData;
    protected ImageIcon gameIcon;
    protected ImageIcon gameMenuImage;
    protected BufferedImage squarAntine ;
    protected BufferedImage triGorath ;
    protected BufferedImage epsilonCircle ;
    protected BufferedImage vertexOnEpsilon;
    protected BufferedImage shot;
    protected BufferedImage collectible;
    protected BufferedImage writOfAceso;
    protected BufferedImage writOfAres;
    protected BufferedImage writOfProteus;
    protected BufferedImage writOfAstrape;
    protected BufferedImage writOfCerberus;
    protected BufferedImage writOfChiron;
    protected BufferedImage writOfDolus;
    protected BufferedImage writOfEmpusa;
    protected BufferedImage writOfMelampus;

    protected BufferedImage omenoct;

    protected BufferedImage necropick;
    protected BufferedImage archmire;
    protected BufferedImage wyrm;
    protected BufferedImage orb ;
    protected BufferedImage barricados ;

    protected BufferedImage smileyFace;
    protected BufferedImage smileyLeftHand ;
    protected BufferedImage smileyRightHand ;
    protected BufferedImage smileyPunch;

    protected BufferedImage headSkeleton ;
    protected BufferedImage writOfAthena;

    protected Font font10;
    protected Font font12;
    protected Font font15;
    protected Font font20;
    protected Font font22;
    protected Font font35;

    public String getEnemyDieSound() {
        return enemyDieSound;
    }

    public void setEnemyDieSound(String enemyDieSound) {
        this.enemyDieSound = enemyDieSound;
    }

    private String enemyDieSound = "Sounds/died.wav";
    private String endOfEachWaveSound = "Sounds/fire.wav";
    private String endOfGameSound = "Sounds/jump.wav";
    private String enterAnEnemy = "Sounds/shoot.wav";

    private MyProjectData() {
        importImages();
        importFonts();
        importObjectsInGame();
    }

    public static MyProjectData getProjectData() {
        if (projectData == null) {
            projectData = new MyProjectData();
        }
        return projectData;
    }
    private void importImages(){
        gameMenuImage = new ImageIcon("Game/GameMenuImage.jpg");
        gameIcon = new ImageIcon("Game/GameIcon.ico");

        try {
            String pathBackground = "Game/Writ Of Athena.png";
            File fileBackground = new File(pathBackground);
            writOfAthena = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/head dead.png";
            File fileBackground = new File(pathBackground);
            headSkeleton = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/Punch.gif";
            File fileBackground = new File(pathBackground);
            smileyPunch = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/RightHand.png";
            File fileBackground = new File(pathBackground);
            smileyRightHand = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/LeftHand.png";
            File fileBackground = new File(pathBackground);
            smileyLeftHand = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/SmileyFace.png";
            File fileBackground = new File(pathBackground);
            smileyFace = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Dolus.png";
            File fileBackground = new File(pathBackground);
            writOfDolus = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Empusa.png";
            File fileBackground = new File(pathBackground);
            writOfEmpusa = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Chiron.png";
            File fileBackground = new File(pathBackground);
            writOfChiron = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Melampus.png";
            File fileBackground = new File(pathBackground);
            writOfMelampus = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Cerberus.png";
            File fileBackground = new File(pathBackground);
            writOfCerberus = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Astrape.png";
            File fileBackground = new File(pathBackground);
            writOfAstrape = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/wyrm.png";
            File fileBackground = new File(pathBackground);
            wyrm = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/orb.png";
            File fileBackground = new File(pathBackground);
            orb = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/Barricados.png";
            File fileBackground = new File(pathBackground);
            barricados = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/archmire.png";
            File fileBackground = new File(pathBackground);
            archmire = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/omenoct.png";
            File fileBackground = new File(pathBackground);
            omenoct = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/necropick.png";
            File fileBackground = new File(pathBackground);
            necropick = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/square.png";
            File fileBackground = new File(pathBackground);
            squarAntine = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Enemy/triangle.png";
            File fileBackground = new File(pathBackground);
            triGorath = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Epsilon/Epsilon_Circle.png";
            File fileBackground = new File(pathBackground);
            epsilonCircle = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Epsilon/Shot.png";
            File fileBackground = new File(pathBackground);
            shot = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Epsilon/coin.png";
            File fileBackground = new File(pathBackground);
            collectible = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Aceso.png";
            File fileBackground = new File(pathBackground);
            writOfAceso = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Ares.png";
            File fileBackground = new File(pathBackground);
            writOfAres = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String pathBackground = "Game/Writ of Proteus.png";
            File fileBackground = new File(pathBackground);
            writOfProteus = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String pathBackground = "Epsilon/circle.png";
            File fileBackground = new File(pathBackground);
            vertexOnEpsilon = ImageIO.read(fileBackground);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void importFonts() {

        font10 = new Font("Comic Sans MS", Font.PLAIN, 10);
        font12 = new Font("Comic Sans MS", Font.PLAIN, 12);
        font15 = new Font("Comic Sans MS", Font.PLAIN, 15);
        font20 = new Font("Comic Sans MS", Font.PLAIN, 20);
        font22 = new Font("Comic Sans MS", Font.PLAIN, 22);
        font35 = new Font("Comic Sans MS", Font.PLAIN, 35);

    }
    private void importObjectsInGame(){

    }

    public ImageIcon getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(ImageIcon gameIcon) {
        this.gameIcon = gameIcon;
    }

    public ImageIcon getGameMenuImage() {
        return gameMenuImage;
    }

    public void setGameMenuImage(ImageIcon gameMenuImage) {
        this.gameMenuImage = gameMenuImage;
    }

    public Font getFont10() {
        return font10;
    }

    public void setFont10(Font font10) {
        this.font10 = font10;
    }

    public Font getFont12() {
        return font12;
    }

    public void setFont12(Font font12) {
        this.font12 = font12;
    }

    public Font getFont15() {
        return font15;
    }

    public void setFont15(Font font15) {
        this.font15 = font15;
    }

    public Font getFont20() {
        return font20;
    }

    public void setFont20(Font font20) {
        this.font20 = font20;
    }

    public Font getFont22() {
        return font22;
    }

    public void setFont22(Font font22) {
        this.font22 = font22;
    }

    public Font getFont35() {
        return font35;
    }

    public void setFont35(Font font35) {
        this.font35 = font35;
    }

    public BufferedImage getSquarAntine() {
        return squarAntine;
    }

    public void setSquarAntine(BufferedImage squarAntine) {
        this.squarAntine = squarAntine;
    }

    public BufferedImage getTriGorath() {
        return triGorath;
    }

    public void setTriGorath(BufferedImage triGorath) {
        this.triGorath = triGorath;
    }

    public BufferedImage getEpsilonCircle() {
        return epsilonCircle;
    }

    public void setEpsilonCircle(BufferedImage epsilonCircle) {
        this.epsilonCircle = epsilonCircle;
    }

    public BufferedImage getShot() {
        return shot;
    }

    public void setShot(BufferedImage shot) {
        this.shot = shot;
    }

    public BufferedImage getCollectible() {
        return collectible;
    }

    public void setCollectible(BufferedImage collectible) {
        this.collectible = collectible;
    }

    public BufferedImage getWritOfAceso() {
        return writOfAceso;
    }

    public void setWritOfAceso(BufferedImage writOfAceso) {
        this.writOfAceso = writOfAceso;
    }

    public BufferedImage getWritOfAres() {
        return writOfAres;
    }

    public void setWritOfAres(BufferedImage writOfAres) {
        this.writOfAres = writOfAres;
    }

    public BufferedImage getWritOfProteus() {
        return writOfProteus;
    }

    public void setWritOfProteus(BufferedImage writOfProteus) {
        this.writOfProteus = writOfProteus;
    }

    public String getEndOfEachWaveSound() {
        return endOfEachWaveSound;
    }

    public void setEndOfEachWaveSound(String endOfEachWaveSound) {
        this.endOfEachWaveSound = endOfEachWaveSound;
    }

    public String getEndOfGameSound() {
        return endOfGameSound;
    }

    public void setEndOfGameSound(String endOfGameSound) {
        this.endOfGameSound = endOfGameSound;
    }

    public String getEnterAnEnemy() {
        return enterAnEnemy;
    }

    public void setEnterAnEnemy(String enterAnEnemy) {
        this.enterAnEnemy = enterAnEnemy;
    }

    public BufferedImage getVertexOnEpsilon() {
        return vertexOnEpsilon;
    }

    public void setVertexOnEpsilon(BufferedImage vertexOnEpsilon) {
        this.vertexOnEpsilon = vertexOnEpsilon;
    }

    public BufferedImage getOmenoct() {
        return omenoct;
    }

    public void setOmenoct(BufferedImage omenoct) {
        this.omenoct = omenoct;
    }

    public BufferedImage getNecropick() {
        return necropick;
    }

    public BufferedImage getArchmire() {
        return archmire;
    }

    public void setArchmire(BufferedImage archmire) {
        this.archmire = archmire;
    }

    public void setNecropick(BufferedImage necropick) {
        this.necropick = necropick;
    }

    public BufferedImage getWyrm() {
        return wyrm;
    }

    public void setWyrm(BufferedImage wyrm) {
        this.wyrm = wyrm;
    }

    public BufferedImage getBarricados() {
        return barricados;
    }

    public void setBarricados(BufferedImage barricados) {
        this.barricados = barricados;
    }

    public BufferedImage getOrb() {
        return orb;
    }

    public void setOrb(BufferedImage orb) {
        this.orb = orb;
    }

    public BufferedImage getWritOfAstrape() {
        return writOfAstrape;
    }

    public void setWritOfAstrape(BufferedImage writOfAstrape) {
        this.writOfAstrape = writOfAstrape;
    }

    public BufferedImage getWritOfCerberus() {
        return writOfCerberus;
    }

    public void setWritOfCerberus(BufferedImage writOfCerberus) {
        this.writOfCerberus = writOfCerberus;
    }

    public BufferedImage getWritOfChiron() {
        return writOfChiron;
    }

    public void setWritOfChiron(BufferedImage writOfChiron) {
        this.writOfChiron = writOfChiron;
    }

    public BufferedImage getWritOfDolus() {
        return writOfDolus;
    }

    public void setWritOfDolus(BufferedImage writOfDolus) {
        this.writOfDolus = writOfDolus;
    }

    public BufferedImage getWritOfEmpusa() {
        return writOfEmpusa;
    }

    public void setWritOfEmpusa(BufferedImage writOfEmpusa) {
        this.writOfEmpusa = writOfEmpusa;
    }

    public BufferedImage getWritOfMelampus() {
        return writOfMelampus;
    }

    public void setWritOfMelampus(BufferedImage writOfMelampus) {
        this.writOfMelampus = writOfMelampus;
    }

    public BufferedImage getSmileyFace() {
        return smileyFace;
    }

    public void setSmileyFace(BufferedImage smileyFace) {
        this.smileyFace = smileyFace;
    }

    public BufferedImage getSmileyLeftHand() {
        return smileyLeftHand;
    }

    public void setSmileyLeftHand(BufferedImage smileyLeftHand) {
        this.smileyLeftHand = smileyLeftHand;
    }

    public BufferedImage getSmileyRightHand() {
        return smileyRightHand;
    }

    public void setSmileyRightHand(BufferedImage smileyRightHand) {
        this.smileyRightHand = smileyRightHand;
    }

    public BufferedImage getSmileyPunch() {
        return smileyPunch;
    }

    public void setSmileyPunch(BufferedImage smileyPunch) {
        this.smileyPunch = smileyPunch;
    }

    public BufferedImage getHeadSkeleton() {
        return headSkeleton;
    }

    public void setHeadSkeleton(BufferedImage headSkeleton) {
        this.headSkeleton = headSkeleton;
    }

    public BufferedImage getWritOfAthena() {
        return writOfAthena;
    }

    public void setWritOfAthena(BufferedImage writOfAthena) {
        this.writOfAthena = writOfAthena;
    }
}