package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Terrorist {

    private BufferedImage terrorist;
    private int x = 450;
    private final int y = Window.getWINDOW_HEIGHT()-120 ;



    public Terrorist(){



        try{
            this.terrorist = ImageIO.read(new File("src/main/resources/terr-removebg-preview.png"));
        }catch (Exception e){
            e.printStackTrace();
        }




    }


    public void paintTerrorist(Graphics graphics) {
        int newWidth = 50;
        int newHeight = 76;
        Image scaledImage = this.terrorist.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }
}
