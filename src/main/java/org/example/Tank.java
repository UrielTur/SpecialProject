package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tank {

    private Image tank;
    private byte x;
    private final short width = 330;
    private final short height = 340;
    private final short y = (short) (Window.getWINDOW_HEIGHT()-285);


    public Tank(){

        this.tank = new ImageIcon("src/main/resources/Tank.png").getImage();

    }


    public void paintTank(Graphics graphics) {
        graphics.drawImage(this.tank,  this.x, this.y,width,height,null);

    }


    public byte getX() {
        return x;
    }

    public void move(double dx) {
        this.x += dx;
    }

}
