package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {

    private final Tank tank;
    private final Terrorist terrorist;







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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tank.move(3);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
