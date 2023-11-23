package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GameScene extends JPanel implements KeyListener {

    private final Tank tank;
    private final Rocket rocket;
    private Terrorist terrorist1;
    private Terrorist terrorist2;
    private Terrorist terrorist3;
    private Terrorist terrorist4;
    private Terrorist terrorist5;
    private List<Terrorist> terrorists;

    private Soldier soldier1;
    private Soldier soldier2;
    private List<Soldier> soldiers;


    private final OptionsScreen optionsScreen;
    private final ImageIcon background1 = new ImageIcon("src/main/resources/GameBackground.png");
    private final ImageIcon background2 = new ImageIcon("src/main/resources/OppositeBackground1.png");

    private int xOfBackground1 = 0;
    private int xOfBackground2 = ((Window.getWINDOW_WIDTH())*2);
    private int yOfBackground1 = -35;
    private int yOfBackground2 = -35;
    private boolean isFire = false;
    private boolean collision = false;
    private boolean isPassLimit = false;
    
    private int indexTerrorist = 0;
    private int indexSoldier = 0;





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

        this.terrorists.get(0).paintTerrorist(graphics);
        this.terrorists.get(1).paintTerrorist(graphics);
        this.terrorists.get(2).paintTerrorist(graphics);
        this.terrorists.get(3).paintTerrorist(graphics);
        this.terrorists.get(4).paintTerrorist(graphics);
        
        this.soldiers.get(0).paintSoldier(graphics);
        this.soldiers.get(1).paintSoldier(graphics);


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

                if (this.tank.getX() > 66){

                    this.terrorists.get(0).move(1);
                    this.terrorists.get(1).move(1);
                    this.terrorists.get(2).move(1);
                    this.terrorists.get(3).move(1);
                    this.terrorists.get(4).move(1);
                    
                    this.soldiers.get(0).move(1);
                    this.soldiers.get(1).move(1);
                    
                }

                if (isFire){
                    this.rocket.fire();
                }



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


    public void collision() {
        
        if (terrorist1.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            collision = true;
            indexTerrorist = 0;
        }
        if (terrorist2.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            collision = true;
            indexTerrorist = 1;
        }
        if (terrorist3.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            collision = true;
            indexTerrorist = 2;
        }
        if (terrorist4.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            collision = true;
            indexTerrorist = 3;
        }
        if (terrorist5.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            collision = true;
            indexTerrorist = 4;
        }
        
        if (soldier1.catchTheSoldier().intersects(this.rocket.calculateRectangle())){
            collision = true;
            indexSoldier =0;
        }
        if (soldier2.catchTheSoldier().intersects(this.rocket.calculateRectangle())) {
            collision = true;
            indexSoldier = 1;
        }

            
    }


    public void passLimit(){
        if (terrorist1.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 0;
            isPassLimit = true;
        }
        if (terrorist2.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 1;
            isPassLimit = true;
        }
        if (terrorist3.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 2;
            isPassLimit = true;
        }
        if (terrorist4.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 3;
            isPassLimit = true;
        }
        if (terrorist5.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 4;
            isPassLimit = true;
        }


        if (soldier1.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexSoldier = 0;
            isPassLimit = true;
        }
        if (soldier2.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexSoldier = 1;
            isPassLimit = true;
        }


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
            if (this.tank.getX() > 66){
                this.isFire = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

