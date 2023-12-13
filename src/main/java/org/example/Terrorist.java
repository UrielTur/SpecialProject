package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Terrorist {

    private BufferedImage terrorist;
    private short x;
    private final short y;
    private final byte width = 65;
    private final byte height = 85;
    private final Random random;




    public Terrorist(int origin, int bound){
        this.random = new Random();

        this.x = (short) random.nextInt(origin , bound);
        this.y = (short) (Window.getWINDOW_HEIGHT()-155);


        try{
            this.terrorist = ImageIO.read(new File(getClass().getResource("/terr-removebg-preview.png").toURI()));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void paintTerrorist(Graphics graphics) {

        Image scaledImage = this.terrorist.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, (int) this.x, this.y, null);
    }

    public void move(float dx){
        this.x -= (float)dx;
    }


    public float getX() {
        return x;
    }

    public void dead(short origin, short bound){
        this.x = (short) random.nextInt(origin , bound);
    }

    public Rectangle catchTheTerrorist() {
        return new Rectangle ((int) this.x, this.y , this.width, this.height);
    }
    public Rectangle distanceRectangle() {
        return new Rectangle ((int) (this.x - 45), this.y , this.width + 45, this.height);
    }

    public Rectangle distanceRectangleDouble() {
        return new Rectangle ((int) (this.x - 50), this.y , this.width + 50, this.height);
    }
}



