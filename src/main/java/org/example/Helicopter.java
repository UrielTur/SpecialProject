package org.example;

import com.sun.jdi.LocalVariable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Helicopter {

    private final BufferedImage helicopter;
    private final int x = 270;
    private final int y = 150;
    private final int width = 500;
    private final int height = 500;


    public Helicopter() {
        try {
            this.helicopter = ImageIO.read(new File("src/main/resources/Helicopter.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void paintHelicopter(Graphics graphics) {
        Image scaledImage = this.helicopter.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }
}
