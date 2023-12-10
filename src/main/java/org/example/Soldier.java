package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Soldier {

    private BufferedImage soldier;

    private short x;
    private short y;
    private final byte width = 65;
    private final byte height = 85;
    private final Random random;
    private boolean isMove = true;


    public Soldier(short origin, short bound){
        this.random = new Random();

        this.x = (short) random.nextInt(origin , bound);
        this.y = (short) (Window.getWINDOW_HEIGHT()-155);

        try{
            this.soldier = ImageIO.read(new File("src/main/resources/soldier-min.png"));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void paintSoldier(Graphics graphics) {
        Image scaledImage = this.soldier.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        graphics.drawImage(scaledImage, (int) this.x, (int) this.y, null);
    }

    public void move(int dx){
        if (this.x >= (Window.getWINDOW_WIDTH()/2) - 32 && this.isMove) {
            this.x -= dx;
        }

    }
    public void moveUp(int dy){
            this.y -= dy;
    }

    public void setX(short x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public void dead(short origin, short bound){
        this.x = (short) random.nextInt(origin , bound);
        this.y = (short) (Window.getWINDOW_HEIGHT()-155);
    }

    public Rectangle catchTheSoldier() {
        return new Rectangle ((int) this.x, (int) this.y, this.width, this.height);
    }

}
