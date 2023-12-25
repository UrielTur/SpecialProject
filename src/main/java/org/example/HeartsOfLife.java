package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HeartsOfLife {

    private BufferedImage heart1;
    private BufferedImage heart2;
    private BufferedImage heart3;

    private boolean showHeart1 = true;
    private boolean showHeart2 = true;
    private boolean showHeart3 = true;


    private final short x = (short) (Window.getWINDOW_WIDTH() - 170);
    private final byte y = 10;
    private final byte width = 40;
    private final byte height = 40;

    public HeartsOfLife(){

            this.heart1 = new ImageIcon("src/main/resources/heartOflLife.png").getImage();

            this.heart2 = new ImageIcon("src/main/resources/heartOflLife.png").getImage();

            this.heart3 = new ImageIcon("src/main/resources/heartOflLife.png").getImage();

    }


    public void paintHearts(Graphics graphics) {
        if (showHeart1) {
            graphics.drawImage(this.heart1,  this.x,  this.y , width, height,null);
        }
        if (showHeart2) {
            graphics.drawImage(this.heart2,  this.x +50,  this.y , width, height,null);
        }
        if (showHeart3) {
            graphics.drawImage(this.heart3,  this.x + 100,  this.y , width, height,null);

        }
    }


    public void hideHeart3() {
        showHeart3 = false;
    }

    public void hideHeart2() {
        showHeart2 = false;
    }

    public void hideHeart1() {
        showHeart1 = false;
    }
}
