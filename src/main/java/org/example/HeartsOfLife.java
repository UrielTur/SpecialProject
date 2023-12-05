package org.example;

import javax.imageio.ImageIO;
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


    private final int x = Window.getWINDOW_WIDTH() - 170;
    private final int y = 10;
    private final int width = 40;
    private final int height = 40;

    public HeartsOfLife(){

        try{
            this.heart1 = ImageIO.read(new File("src/main/resources/heartOflLife.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            this.heart2 = ImageIO.read(new File("src/main/resources/heartOflLife.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            this.heart3 = ImageIO.read(new File("src/main/resources/heartOflLife.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void paintHearts(Graphics graphics) {
        if (showHeart1) {
            Image scaledImage1 = this.heart1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage1, this.x, this.y, null);
        }
        if (showHeart2) {
            Image scaledImage2 = this.heart2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage2, this.x + 50, this.y, null);
        }
        if (showHeart3) {
            Image scaledImage3 = this.heart3.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage3, this.x + 100, this.y, null);
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
