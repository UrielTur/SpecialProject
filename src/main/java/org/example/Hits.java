package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Hits {
    private BufferedImage hitsIcon;

    private final int x = 10;
    private final int y = 10;
    private final int width = 40;
    private final int height = 40;

    public Hits() {
        try {
            this.hitsIcon = ImageIO.read(new File("src/main/resources/Hits.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintHitsIcon(Graphics graphics) {
        Image scaledImage = this.hitsIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }
}
