package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Hits {
    private BufferedImage hitsIcon;

    private final byte x = 10;
    private final byte y = 10;
    private final byte width = 40;
    private final byte height = 40;

    public Hits() {
        this.hitsIcon = new ImageIcon("src/main/resources/Hits.png").getImage();
    }

    public void paintHitsIcon(Graphics graphics) {
        Image scaledImage = this.hitsIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }
}
