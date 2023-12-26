package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Soldier extends Thread {

    private Image soldier;


    private float x;
    private short y;
    private final byte width = 65;
    private final byte height = 85;
    private final Random random;
    private boolean isMove = false;


    public Soldier(float origin, short bound){
        this.random = new Random();

        this.x = (float) random.nextInt((int) origin, bound);
        this.y = (short) (Window.getWINDOW_HEIGHT()-155);

        this.soldier = new ImageIcon( "src/main/resources/soldier-min.png").getImage();


    }

    public void run(){
        while (true) {
            if (isMove)
                move();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void paintSoldier(Graphics graphics) {
        graphics.drawImage(this.soldier, (int) this.x, this.y,width,height,null);
    }

    public void move(){
        if (this.x >= (Window.getWINDOW_WIDTH()/2) - 32 && this.isMove) {
            this.x -= 1.2;
        }
    }

    public void moveUp(byte dy){
        this.y -= dy;
    }

    public float getX() {
        return x;
    }

    public short getY(){
        return y;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public void dead(short origin, short bound){
        this.x = (short) random.nextInt(origin , bound);
        this.y = (short) (Window.getWINDOW_HEIGHT()-155);
    }

    public Rectangle catchTheSoldier() {
        return new Rectangle ((int) this.x, this.y, this.width, this.height);
    }

    public void setDifficultLevel(byte difficultLevel) {
        this.difficultLevel = difficultLevel;
    }
}
