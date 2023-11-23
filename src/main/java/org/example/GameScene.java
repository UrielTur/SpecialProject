package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {

    private final Tank tank;
    private final Rocket rocket;
    private Terrorist terrorist1;
    private Terrorist terrorist2;
    private Terrorist terrorist3;
    private Terrorist terrorist4;
    private Terrorist terrorist5;

    private final OptionsScreen optionsScreen;
    private final ImageIcon background1 = new ImageIcon("src/main/resources/GameBackground.png");
    private final ImageIcon background2 = new ImageIcon("src/main/resources/OppositeBackground1.png");

    private int xOfBackground1 = 0;
    private int xOfBackground2 = ((Window.getWINDOW_WIDTH())*2);
    private int yOfBackground1 = -35;
    private int yOfBackground2 = -35;
    private boolean isFire = false;
    private boolean collision = false;




    public GameScene(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);

        this.optionsScreen = new OptionsScreen();
        this.tank = new Tank();
        this.rocket = new Rocket();
        this.rocket.setX(tank.getWidth()- rocket.getWidth()+310);

        this.terrorist1 = new Terrorist(Window.getWINDOW_WIDTH()+40,Window.getWINDOW_WIDTH()*2);
        this.terrorist2 = new Terrorist(Window.getWINDOW_WIDTH()+100,Window.getWINDOW_WIDTH()*2);
        this.terrorist3 = new Terrorist(Window.getWINDOW_WIDTH()+160,Window.getWINDOW_WIDTH()*2);
        this.terrorist4 = new Terrorist(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*2);
        this.terrorist5 = new Terrorist(Window.getWINDOW_WIDTH()+250,Window.getWINDOW_WIDTH()*2);
        
        this.soldier1 = new Soldier(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*2);
        this.soldier2 = new Soldier(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*2);



        this.terrorists = List.of(terrorist1, terrorist2, terrorist3, terrorist4, terrorist5);
        this.soldiers = List.of(soldier1, soldier2);

        
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

        this.rocket.paintRocket(graphics);
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

                this.terrorist1.move(1);
                this.terrorist2.move(1);
                this.terrorist3.move(1);
                this.terrorist4.move(1);
                this.terrorist5.move(1);

                if (isFire){
                    this.rocket.fire();
                }

//
//                boolean hasCollision1 = collision(this.terrorist1);
//                boolean hasCollision2 = collision(this.terrorist2);
//                boolean hasCollision3 = collision(this.terrorist3);
//                boolean hasCollision4 = collision(this.terrorist4);
//                boolean hasCollision5 = collision(this.terrorist5);


                this.collision();
                if (collision || this.rocket.getX() > Window.getWINDOW_WIDTH()){
                    terrorists.get(indexTerrorist).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*2);
                    soldiers.get(indexSoldier).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*2);
                    this.rocket.setX(350);
                    isFire = false;
                    collision = false;

                }
                this.passLimit();
                if (isPassLimit){
                    terrorists.get(indexTerrorist).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*2);
                    soldiers.get(indexSoldier).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*2);
                }




                try {
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }).start();


    }


    public void moveBackground() {
        this.xOfBackground1 -= 2;
        this.xOfBackground2 -= 2;
    }


    public boolean collision() {
        if (terrorist1.catchTheTerrorist().intersects(this.rocket.calculateRectangle()) || terrorist2.catchTheTerrorist().intersects(this.rocket.calculateRectangle()) || terrorist3.catchTheTerrorist().intersects(this.rocket.calculateRectangle()) || terrorist4.catchTheTerrorist().intersects(this.rocket.calculateRectangle()) || terrorist5.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            collision = true;
        }

        return collision;
    }








    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.terrorist1.move(1);
            this.terrorist2.move(1);
            this.terrorist3.move(1);
            this.terrorist4.move(1);
            this.terrorist5.move(1);

            this.soldier1.move(1);
            this.soldier2.move(1);

            if (this.tank.getX() <= 66) {
                tank.move(3);
            }
            if (this.tank.getX() > 66){
                moveBackground();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
           this.isFire = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

