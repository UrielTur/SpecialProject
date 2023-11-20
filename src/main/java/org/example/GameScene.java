package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {

    private final Tank tank;
    private Terrorist terrorist1;
    private Terrorist terrorist2;
    private Terrorist terrorist3;
    private Terrorist terrorist4;
    private Terrorist terrorist5;

    private final OptionsScreen optionsScreen;
    private final ImageIcon background1 = new ImageIcon("src/main/resources/Background.png");
    private final ImageIcon background2 = new ImageIcon("src/main/resources/OppositeBackground.png");

    private int xOfBackground1 = 0;
    private int xOfBackground2 = ((Window.getWINDOW_WIDTH())*2);
    private int yOfBackground1 = -35;
    private int yOfBackground2 = -35;




    public GameScene(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);

        this.tank = new Tank();
        this.optionsScreen = new OptionsScreen();

        this.terrorist1 = new Terrorist(Window.getWINDOW_WIDTH()+40,Window.getWINDOW_WIDTH()*2);
        this.terrorist2 = new Terrorist(Window.getWINDOW_WIDTH()+100,Window.getWINDOW_WIDTH()*2);
        this.terrorist3 = new Terrorist(Window.getWINDOW_WIDTH()+160,Window.getWINDOW_WIDTH()*2);
        this.terrorist4 = new Terrorist(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*2);
        this.terrorist5 = new Terrorist(Window.getWINDOW_WIDTH()+250,Window.getWINDOW_WIDTH()*2);




        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);






    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.background1.paintIcon(null,graphics,this.xOfBackground1, this.yOfBackground1);
        this.background2.paintIcon(null,graphics,this.xOfBackground2, this.yOfBackground2);

        this.terrorist1.paintTerrorist(graphics);
        this.terrorist2.paintTerrorist(graphics);
        this.terrorist3.paintTerrorist(graphics);
        this.terrorist4.paintTerrorist(graphics);
        this.terrorist5.paintTerrorist(graphics);

        this.tank.paintTank(graphics);


    }



    public synchronized void mainGameLoop(){
        new Thread(() -> {

            while (true){
                repaint();

                if (this.xOfBackground1 <= -(Window.getWINDOW_WIDTH()*2)){
                    this.xOfBackground1 = (Window.getWINDOW_WIDTH()*2);
                } else if (this.xOfBackground2 <= -(Window.getWINDOW_WIDTH()*2)){
                    this.xOfBackground2 = (Window.getWINDOW_WIDTH()*2);
                }






                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }).start();


    }


    public void moveBackground(){
        this.xOfBackground1 -= 3;
        this.xOfBackground2 -= 3;
    }
    
    public void terroristsMove(){
        for (int i = 0; i <= 5; i++) {
            
        }
        
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

