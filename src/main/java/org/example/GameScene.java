package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {

    private final Tank tank;
    private final Terrorist terrorist;
    private final ImageIcon background1 = new ImageIcon("src/main/resources/Background.png");
    private final ImageIcon background2 = new ImageIcon("src/main/resources/Background.png");

    private int xOfBackground1 = 0;
    private int xOfBackground2 = ((Window.getWINDOW_WIDTH())*2);
    private int yOfBackground1 = -35;
    private int yOfBackground2 = -35;





    public GameScene(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);

        this.tank = new Tank();
        this.terrorist = new Terrorist();


        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);






    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.tank.paintTank(graphics);
        this.terrorist.paintTerrorist(graphics);


    }



    public void mainGameLoop(){
        new Thread(() -> {

            while (true){
                repaint();







            }





        }).start();


    }


    public void moveBackground(){
        this.xOfBackground1 -= 2;
        this.xOfBackground2 -= 2;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (this.tank.getX() <= 66) {
                tank.move(3);
            }
            if (this.tank.getX() > 66){
                moveBackground();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

