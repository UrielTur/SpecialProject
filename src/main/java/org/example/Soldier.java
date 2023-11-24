package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Soldier {

    private BufferedImage soldier;

    private float x;
    private final int y;
    private final int width = 65;
    private final int height = 85;
    private final Random random;


    public Soldier(int origin, int bound){
        this.random = new Random();

        this.x = random.nextInt(origin , bound);
        this.y = Window.getWINDOW_HEIGHT()-155;

        try{
            this.soldier = ImageIO.read(new File("src/main/resources/soldier-min.png"));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void paintSoldier(Graphics graphics) {
        Image scaledImage = this.soldier.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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

    public Rectangle catchTheSoldier() {
        return new Rectangle ((int) this.x, this.y , this.width, this.height);
    }



}
