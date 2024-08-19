package org.example;

import javax.swing.*;
import java.awt.*;

public class Tank {
    private Image tank1;
    private Image tank2;
    private Image tank3;
    private int chosenIndexTank = 1;
    private byte x;
    private final short width = 330;
    private final short height = 340;
    private final short y = (short) (Window.getWINDOW_HEIGHT()-285);


    public Tank(){
        this.tank1 = new ImageIcon("src/main/resources/Tank.png").getImage();
        this.tank2 = new ImageIcon("src/main/resources/secondTank-min.png").getImage();
        this.tank3 = new ImageIcon("src/main/resources/thirdTank-min.png").getImage();
    }


    public void paintTank(Graphics graphics) {
        if (chosenIndexTank == 1) {
            graphics.drawImage(this.tank1, this.x, this.y, width, height, null);
        } else if (chosenIndexTank == 2) {
            graphics.drawImage(this.tank2, this.x, this.y + 12, width, height - 35, null);
        } else if (chosenIndexTank == 3) {
            graphics.drawImage(this.tank3, this.x + 5, this.y + 66, width - 10, height - 170, null);
        }
    }

    public byte getX() {
        return x;
    }

    public void move(double dx) {
        this.x += dx;
    }

    public void setChosenIndexTank(int chosenTank) {
        this.chosenIndexTank = chosenTank;
    }

    public void setX(byte xValue) {
        this.x = xValue;
    }
}
