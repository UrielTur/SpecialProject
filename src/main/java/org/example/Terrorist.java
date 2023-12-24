package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Terrorist extends Thread {

    private Image terrorist;
    private short x;
    private final short y;
    private final byte width = 65;
    private final byte height = 85;
    private final Random random;




    public Terrorist(int origin, int bound){
        this.random = new Random();

        this.x = (short) random.nextInt(origin , bound);
        this.y = (short) (Window.getWINDOW_HEIGHT()-155);


        this.terrorist = new ImageIcon("src/main/resources/terr-removebg-preview.png").getImage();



    }
    public void run(){
        while (true){
            this.x--;

            try {
                Thread.sleep(9);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void paintTerrorist(Graphics graphics) {

//        Image scaledImage = this.terrorist.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(terrorist, (short) this.x, (short) this.y,width,height,null);
    }

//    public void move(float dx){
//        this.x -= (float)dx;
//    }


    public float getX() {
        return x;
    }

    public void dead(short origin, short bound){
        this.x = (short) random.nextInt(origin , bound);
    }

    public Rectangle catchTheTerrorist() {
        return new Rectangle ((int) this.x, this.y , this.width, this.height);
    }
    public Rectangle distanceRectangle() {
        return new Rectangle ((int) (this.x - 45), this.y , this.width + 45, this.height);
    }
    public Rectangle distanceRectangleDouble() {
        return new Rectangle ((int) (this.x - 50), this.y , this.width + 50, this.height);
    }
}



