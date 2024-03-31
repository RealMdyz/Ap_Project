package MyProject;

import javax.swing.*;

public class MyProjectData {

    private static MyProjectData projectData;
    protected ImageIcon gameIcon;
    protected ImageIcon gameMenuImage;

    private MyProjectData() {
        addImages();
    }
    private void addImages() {

    }
    public static MyProjectData getProjectData() {
        if (projectData == null) {
            projectData = new MyProjectData();
        }
        return projectData;
    }
}