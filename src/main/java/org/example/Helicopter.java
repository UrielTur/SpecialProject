package org.example;

import com.sun.jdi.LocalVariable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Helicopter {

    private Image helicopter;
    private final short x = 270;
    private final short y = 150;
    private final short width = 500;
    private final short height = 500;


    public Helicopter() {
            this.helicopter = new ImageIcon("src/main/resources/Helicopter.png").getImage();
    }


    public void paintHelicopter(Graphics graphics) {
        graphics.drawImage(this.helicopter,  this.x, this.y,width,height,null);
    }
}
