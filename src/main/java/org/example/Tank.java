package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tank {

    private BufferedImage tank;
    private byte x;
    private final short width = 330;
    private final short height = 340;
    private final short y = (short) (Window.getWINDOW_HEIGHT()-285);


    public Tank(){


        try{
            this.tank = ImageIO.read(new File(getClass().getResource("/Tank.png").toURI()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void paintTank(Graphics graphics) {
//        this.width = 330;
//        this.height = 340;
        Image scaledImage = this.tank.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }


    public byte getX() {
        return x;
    }

    public void move(double dx) {
        this.x += dx;
    }

}
