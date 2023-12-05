package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Terrorist {

    private BufferedImage terrorist;
    private float x;
    private final int y;
    private int width = 65;
    private int height = 85;
    private Random random;




    public Terrorist(int origin, int bound){
        this.random = new Random();

        this.x = random.nextInt(origin , bound);
        this.y = Window.getWINDOW_HEIGHT()-155;

        try{
            this.terrorist = ImageIO.read(new File("src/main/resources/terr-removebg-preview.png"));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void paintTerrorist(Graphics graphics) {

        Image scaledImage = this.terrorist.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, (int) this.x, this.y, null);
    }

    public void move(int dx){
        this.x -= dx;
    }


    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void dead(int origin, int bound){
        this.x = random.nextInt(origin , bound);
    }

    public Rectangle catchTheTerrorist() {
        return new Rectangle ((int) this.x, this.y , this.width, this.height);
    }
    public Rectangle distanceRectangle() {
        return new Rectangle ((int) this.x - 45, this.y , this.width + 45, this.height);
    }

    public Rectangle distanceRectangleDouble() {
        return new Rectangle ((int) this.x - 50, this.y , this.width + 50, this.height);
    }
}



