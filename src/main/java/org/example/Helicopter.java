package org.example;

import com.sun.jdi.LocalVariable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Helicopter {

    private final BufferedImage helicopter;
    private final short x = 270;
    private final short y = 150;
    private final short width = 500;
    private final short height = 500;


    public Helicopter() {
        try {
            this.helicopter = ImageIO.read(new File(getClass().getResource("/Helicopter.png").toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void paintHelicopter(Graphics graphics) {
        Image scaledImage = this.helicopter.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }
}
