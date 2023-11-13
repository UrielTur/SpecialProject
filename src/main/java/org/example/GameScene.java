package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {

    private Tank tank;
    private Terrorist terrorist;
    private final boolean[] pressedKey;







    public GameScene(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);
        this.pressedKey = new boolean[1];

        this.tank = new Tank();
        this.terrorist = new Terrorist();

        mainGameLoop();
//        this.setDoubleBuffered(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();





    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.tank.paintTank(graphics);
        this.terrorist.paintTerrorist(graphics);

    }



    public synchronized void mainGameLoop(){
        new Thread(() -> {

            while (true){
                repaint();



            }





        }).start();


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tank.move(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
