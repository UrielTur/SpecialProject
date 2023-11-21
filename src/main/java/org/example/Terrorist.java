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



    public Terrorist(int origin, int bound){
        Random random = new Random();

        this.x = random.nextInt(origin , bound);
        this.y = Window.getWINDOW_HEIGHT()-155;

        try{
            this.terrorist = ImageIO.read(new File("src/main/resources/terr-removebg-preview.png"));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void paintTerrorist(Graphics graphics) {
        int newWidth = 65;
        int newHeight = 85;
        Image scaledImage = this.terrorist.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, (int) this.x, this.y, null);
    }

    public void move(int dx){
        this.x -= dx;
    }
}
