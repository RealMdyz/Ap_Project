package Models;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ObjectsInGame extends JLabel {


    private int x;
    private int xCenter;
    private int y;
    private int yCenter;
    private int hp = 10;
    private int width;
    private int height;
    private int xVelocity = 0;
    private int yVelocity = 0;

    private double angleForRotate = 0;
    private double xVelocityImpact = 0;
    private double yVelocityImpact = 0;
    private long impactTime = 0;

    protected BufferedImage background;

    public ObjectsInGame(int x, int y, int hp) {

        this.x = x;
        this.y = y;
        this.hp = hp;

    }



    public void doImpact(int xImpact, int yImpact,  int dis) {
        this.setxCenter(this.getX() + (int)this.getWidth() / 2);
        this.setyCenter(this.getY() + (int)this.getHeight() / 2);
        // Calculate the direction vector from the impact point to the object's position
        double dx = getxCenter() - xImpact;
        double dy = getyCenter() - yImpact;

        // Calculate the distance from the impact point to the object's position
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Adjust speed as needed
        double speed = Constant.getSpeedOfImpact();
        double vx, vy;
        vx = 0;
        vy = 0;
        // Calculate the impact percentage using sigmoid function
        double impactPercentage = 1 / (1 + Math.exp(-Constant.ALPHA * (distance - (double) Constant.MAX_DISTANCE / 2)));
        if(distance < dis){
            vx = speed * dx / distance * impactPercentage;
            vy = speed * dy / distance * impactPercentage;
            impactTime = System.currentTimeMillis();

            if(this.hp == 15){
                setAngleForRotate(Constant.getRotateAfterImpact());
            }
        }
        // Adjust velocity based on impact percentage



        setxVelocityImpact(vx);
        setyVelocityImpact(vy);
       // System.out.println(distance + " " + hp);
    }

    public void rotateImage(double degrees) {
        // Ensure the background image is not null
        if (background == null) {
            System.out.println("Background image is null. Exiting rotateImage function.");
            return;
        }

        // Create a new BufferedImage to hold the rotated image
        int width = Constant.getHeightOfSquarantine();
        int height = Constant.getHeightOfSquarantine();
        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Get the graphics context of the rotated image
        Graphics2D g2D = rotatedImage.createGraphics();

        // Set rendering hints for high-quality rendering
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Calculate the center of rotation
        double centerX = width / 2.0;
        double centerY = height / 2.0;

        // Create an AffineTransform to perform the rotation around the center of the image
        AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(degrees), centerX, centerY);

        // Apply the rotation transformation
        g2D.setTransform(rotation);

        // Draw the original image onto the rotated image
        g2D.drawImage(background, 0, 0, width, height, null);

        // Dispose the graphics context
        g2D.dispose();

        // Calculate the bounds of the rotated image
        Rectangle bounds = rotation.createTransformedShape(new Rectangle(width, height)).getBounds();
        int newWidth = bounds.width;
        int newHeight = bounds.height;

        // Update the object's dimensions based on the rotated image dimensions
        setWidth(newWidth);
        setHeight(newHeight);

        // Adjust the position of the rotated image to keep it within bounds
        int deltaX = bounds.x;
        int deltaY = bounds.y;
        setX(getX() + deltaX);
        setY(getY() + deltaY);

        // Set the rotated image as the new background
        changeBackground(rotatedImage);

        // Repaint the component to reflect the changes
        repaint();
    }


    public void changeBackground(BufferedImage newBackground) {
        this.background = newBackground;
        repaint();
    }



    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getxVelocityImpact() {
        return xVelocityImpact;
    }

    public void setxVelocityImpact(double xVelocityImpact) {
        this.xVelocityImpact = xVelocityImpact;
    }

    public double getyVelocityImpact() {
        return yVelocityImpact;
    }

    public void setyVelocityImpact(double yVelocityImpact) {
        this.yVelocityImpact = yVelocityImpact;
    }

    public long getImpactTime() {
        return impactTime;
    }

    public void setImpactTime(long impactTime) {
        this.impactTime = impactTime;
    }

    public int getxCenter() {
        return xCenter;
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return yCenter;
    }

    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }

    public double getAngleForRotate() {
        return angleForRotate;
    }

    public void setAngleForRotate(double angleForRotate) {
        this.angleForRotate = angleForRotate;
    }
}