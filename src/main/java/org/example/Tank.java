package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tank {

    private BufferedImage tank;
    private int x;
    private final int y = Window.getWINDOW_HEIGHT()-285 ;



    public Tank(){

        try{
            this.tank = ImageIO.read(new File("src/main/resources/Tank.png"));
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    public void paintTank(Graphics graphics) {
        int newWidth = 330;
        int newHeight = 340;
        Image scaledImage = this.tank.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }

    public int getX() {
        return x;
    }

    public void move(double dx) {
        this.x += dx;
    }

}
