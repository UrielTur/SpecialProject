package org.example;

import javax.swing.*;
import java.awt.*;

public class Hits {
    private Image hitsIcon;
    private final byte x = 10;
    private final byte y = 10;
    private final byte width = 40;
    private final byte height = 40;


    public Hits() {
        this.hitsIcon = new ImageIcon("src/main/resources/Hits.png").getImage();
    }

    public void paintHitsIcon(Graphics graphics) {
        graphics.drawImage(this.hitsIcon,  this.x,  this.y , width, height,null);
    }
}
