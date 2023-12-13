package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Rocket {

    private short x;
    private final short y;
    private final byte width = 50;
    private final byte height = 12;

    private BufferedImage rocket;


    public Rocket(){
        this.x = 260;
        this.y = Window.getWINDOW_HEIGHT()-146;




        try{
            this.rocket = ImageIO.read(new File(getClass().getResource("/Rocket.png").toURI()));
        }catch (Exception e){
            e.printStackTrace();
        }



    }




    public void paintRocket(Graphics graphics) {
//        this.width = 50;
//        this.height = 12;
        Image scaledImage = this.rocket.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }


    public void fire() {
            this.x += 4;
    }


    public Rectangle calculateRectangle() {
        return new Rectangle(this.x, this.y, this.width , this.height);
    }


    public void setX(short x) {
        this.x = x;
    }

    public short getX() {
        return x;
    }

}
