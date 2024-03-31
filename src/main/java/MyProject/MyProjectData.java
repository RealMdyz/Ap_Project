package MyProject;

import javax.swing.*;
import java.awt.*;

public class MyProjectData {

    private static MyProjectData projectData;
    protected ImageIcon gameIcon;
    protected ImageIcon gameMenuImage;

    protected Font font10;
    protected Font font12;
    protected Font font15;
    protected Font font20;
    protected Font font22;
    protected Font font35;

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
        gameIcon = new ImageIcon("Game/GameIcon.jpeg");
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
}