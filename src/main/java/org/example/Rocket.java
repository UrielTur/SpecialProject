package org.example;
import javax.swing.*;
import java.awt.*;


public class Rocket {

    private short x;
    private final short y;
    private final byte width = 50;
    private final byte height = 12;

    private Image rocket;


    public Rocket(){
        this.x = 260;
        this.y = (short) (Window.getWINDOW_HEIGHT()-146);
        this.rocket = new ImageIcon("src/main/resources/Rocket.png").getImage();

    }




    public void paintRocket(Graphics graphics) {
//        this.width = 50;
//        this.height = 12;
        Image scaledImage = this.rocket.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, this.x, this.y, null);
    }


    public void fire() {
            this.x += 4;
    }


    public Rectangle calculateRectangle() {
        return new Rectangle(this.x, this.y, this.width , this.height);
    }


    public void setX(short x) {
        this.x = x;
    }

    public short getX() {
        return x;
    }

}
