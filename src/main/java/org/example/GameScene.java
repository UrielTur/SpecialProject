package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GameScene extends JPanel implements KeyListener {

    private final Tank tank;
    private final Helicopter helicopter;
    private final Rocket rocket;
    private Terrorist terrorist1;
    private Terrorist terrorist2;
    private Terrorist terrorist3;
    private Terrorist terrorist4;
    private Terrorist terrorist5;
//    private List<Terrorist> terrorists;
    private Terrorist[] terrorists;
    private final Soldier soldier1;
    private final Soldier soldier2;
    private final List<Soldier> soldiers;
    private final HeartsOfLife heartsOfLife;
    private final Hits hits;


    private final OptionsScreen optionsScreen;
    private final ImageIcon background1 = new ImageIcon("src/main/resources/GameBackground.png");
    private final ImageIcon background2 = new ImageIcon("src/main/resources/OppositeBackground1.png");

    private float xOfBackground1 = 0;
    private float xOfBackground2 =((Window.getWINDOW_WIDTH())*2);
    private final byte yOfBackground1 = -35;
    private final byte yOfBackground2 = -35;
    private boolean isFire = false;
    private boolean terroristHasCollision = false;
    private boolean terroristPassLimit = false;
    private boolean soldierHasCollision = false;
    private boolean soldier1PassLimit = false;
    private boolean soldier2PassLimit = false;

    private byte indexTerrorist = 0;
    private byte indexSoldier = 0;
    private short counterOfSurvivors = 0;
    private short counterOfHits = 0;
    private byte counterOfMisses = 0;
    private final byte easy = 10;
    private final byte medium = 5;
    private final byte hard = 3;
    private byte difficultLevel = easy;

    private final JLabel labelHits;
    private final JLabel labelSurvivors;

    private final FireSound fireSound;




    public GameScene(){

        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);

        this.fireSound = new FireSound();

        this.optionsScreen = new OptionsScreen();
        this.tank = new Tank();
        this.rocket = new Rocket();
        this.helicopter = new Helicopter();


        this.terrorist1 = new Terrorist(Window.getWINDOW_WIDTH()+40,Window.getWINDOW_WIDTH()*6);
        this.terrorist2 = new Terrorist(Window.getWINDOW_WIDTH()+100,Window.getWINDOW_WIDTH()*6);
        this.terrorist3 = new Terrorist(Window.getWINDOW_WIDTH()+160,Window.getWINDOW_WIDTH()*6);
        this.terrorist4 = new Terrorist(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*6);
        this.terrorist5 = new Terrorist(Window.getWINDOW_WIDTH()+250,Window.getWINDOW_WIDTH()*6);
        
        this.soldier1 = new Soldier((short) (Window.getWINDOW_WIDTH()+200), (short) (Window.getWINDOW_WIDTH()*6));
        this.soldier2 = new Soldier((short) (Window.getWINDOW_WIDTH()+200), (short) (Window.getWINDOW_WIDTH()*6));

        this.terrorists = new Terrorist[5];
        for (int i = 0; i < 5; i++) {
            this.terrorists[i] = new Terrorist(Window.getWINDOW_WIDTH()+(40*(i+1)),Window.getWINDOW_WIDTH()*6);
            this.terrorists[i].start();
        }








        this.soldiers = List.of(soldier1, soldier2);

        this.labelHits = new JLabel(String.valueOf(counterOfHits));
        this.labelHits.setBounds(65,10,200,40);
        this.labelHits.setFont(new Font("Arial" , Font.BOLD, 30));
        this.add(labelHits);

        this.labelSurvivors = new JLabel("Survivors " + String.valueOf(counterOfSurvivors));
        this.labelSurvivors.setBounds(10,60,200,40);
        this.labelSurvivors.setFont(new Font("Arial" , Font.BOLD, 30));
        this.add(labelSurvivors);



        this.heartsOfLife = new HeartsOfLife();
        this.hits = new Hits();
        mainGameLoop();

        
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.background1.paintIcon(null,graphics, (int) this.xOfBackground1, this.yOfBackground1);
        this.background2.paintIcon(null,graphics, (int) this.xOfBackground2, this.yOfBackground2);
        this.helicopter.paintHelicopter(graphics);

        for (int i = 0; i < 5; i++) {
            this.terrorists[i].paintTerrorist(graphics);

            if (i < 2){
                this.soldiers.get(i).paintSoldier(graphics);
            }
        }

//        this.terrorists.get(0).paintTerrorist(graphics);
//        this.terrorists.get(1).paintTerrorist(graphics);
//        this.terrorists.get(2).paintTerrorist(graphics);
//        this.terrorists.get(3).paintTerrorist(graphics);
//        this.terrorists.get(4).paintTerrorist(graphics);
//
//        this.soldiers.get(0).paintSoldier(graphics);
//        this.soldiers.get(1).paintSoldier(graphics);

        this.rocket.paintRocket(graphics);
        this.tank.paintTank(graphics);

        this.labelHits.setText(String.valueOf(counterOfHits));
        this.labelSurvivors.setText("Survivors " + String.valueOf(counterOfSurvivors));
        this.heartsOfLife.paintHearts(graphics);
        this.hits.paintHitsIcon(graphics);
    }


    public void mainGameLoop(){
        new Thread(() -> {

            while (counterOfMisses < 3){
                repaint();

                if (this.xOfBackground1 <= -(Window.getWINDOW_WIDTH()*2)){
                    this.xOfBackground1 = (short) (Window.getWINDOW_WIDTH()*2);
                } else if (this.xOfBackground2 <= -(Window.getWINDOW_WIDTH()*2)){
                    this.xOfBackground2 = (short) (Window.getWINDOW_WIDTH()*2);
                }

                if (this.tank.getX() > 66){

//                    this.terrorists.get(0).move((float) 0.0001);
//                    this.terrorists.get(1).move((float) 0.0001);
//                    this.terrorists.get(2).move((float) 0.0001);
//                    this.terrorists.get(3).move((float) 0.0001);
//                    this.terrorists.get(4).move((float) 0.0001);
//
//                    this.soldiers.get(0).move((float) 0.0001);
//                    this.soldiers.get(1).move((float) 0.0001);

//                    movePlayers();
                    moveBackground();
                }


                if (isFire){
                    this.rocket.fire();
                }


                if (terroristHasCollision || soldierHasCollision || this.rocket.getX() > Window.getWINDOW_WIDTH()){
                    this.rocket.setX((short) 350);
                    isFire = false;
                }
                if (soldierHasCollision){
                    soldiers.get(indexSoldier).dead((short) (Window.getWINDOW_WIDTH()+250), (short) (Window.getWINDOW_WIDTH()*5));
                    this.counterOfMisses++;
                    System.out.println(counterOfMisses);
                    soldierHasCollision = false;
                }
                if (terroristHasCollision){
                    terrorists.get(indexTerrorist).dead((short) (Window.getWINDOW_WIDTH()+250), (short) (Window.getWINDOW_WIDTH()*5));
                    this.counterOfHits++;
                    terroristHasCollision = false;
                }

                if (terroristPassLimit){
                    terrorists[indexTerrorist].dead((short) (Window.getWINDOW_WIDTH()+250), (short) (Window.getWINDOW_WIDTH()*5));
                    this.counterOfMisses++;
                    this.terroristPassLimit = false;
                }



                if (soldier1PassLimit) {
                    soldiers.get(0).moveUp((byte) 2);
                    if (soldiers.get(0).getY() <= Window.getWINDOW_HEIGHT()/3) {
                        soldiers.get(0).dead((short) (Window.getWINDOW_WIDTH() + 250), (short) (Window.getWINDOW_WIDTH()*5));
                        this.counterOfSurvivors++;
                        this.soldier1PassLimit = false;
                    }
                }
                if (soldier2PassLimit) {
                    soldiers.get(1).moveUp((byte) 2);
                    if (soldiers.get(1).getY() <= Window.getWINDOW_HEIGHT()/3) {
                        soldiers.get(1).dead((short) (Window.getWINDOW_WIDTH() + 250), (short) (Window.getWINDOW_WIDTH()*5));
                        this.counterOfSurvivors++;
                        this.soldier2PassLimit = false;
                    }
                }


                if (counterOfMisses == 1){
                    heartsOfLife.hideHeart3();
                } else if (counterOfMisses == 2) {
                    heartsOfLife.hideHeart2();
                } else if (counterOfMisses == 3) {
                    heartsOfLife.hideHeart1();
                }


                if (this.counterOfHits > 15 &&  this.counterOfHits <= 30) {
                    this.difficultLevel = medium;
                } else if (this.counterOfHits > 30) {
                    this.difficultLevel = hard;
                }


                try {
                    Thread.sleep(this.difficultLevel);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                checkCollision();
//                safetyDistance();
                checkPassLimit();

            }

        }).start();

    }

//    public void movePlayers(){
//        this.terrorists.get(0).move((float) 1.5);
//        this.terrorists.get(1).move((float) 1.5);
//        this.terrorists.get(2).move((float) 1.5);
//        this.terrorists.get(3).move((float) 1.5);
//        this.terrorists.get(4).move((float) 1.5);
//
//        this.soldiers.get(0).move((float) 1.5);
//        this.soldiers.get(1).move((float) 1.5);
//    }
    public void moveBackground() {
        this.xOfBackground1 -= 1;
        this.xOfBackground2 -= 1;
    }


    public void checkCollision() {

        for (int i = 0; i < terrorists.length; i++) {
            if (terrorists[i].catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
                terroristHasCollision = true;
                indexTerrorist = (byte) i;
            }
        }

//        if (terrorist1.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
//            terroristHasCollision = true;
//            indexTerrorist = 0;
//        }
//        if (terrorist2.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
//            terroristHasCollision = true;
//            indexTerrorist = 1;
//        }
//        if (terrorist3.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
//            terroristHasCollision = true;
//            indexTerrorist = 2;
//        }
//        if (terrorist4.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
//            terroristHasCollision = true;
//            indexTerrorist = 3;
//        }
//        if (terrorist5.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
//            terroristHasCollision = true;
//            indexTerrorist = 4;
//        }
        if (soldier1.catchTheSoldier().intersects(this.rocket.calculateRectangle())){
            soldierHasCollision = true;
            indexSoldier = 0;
        }
        if (soldier2.catchTheSoldier().intersects(this.rocket.calculateRectangle())) {
            soldierHasCollision = true;
            indexSoldier = 1;
        }
    }


    public void checkPassLimit(){

        if (terrorist1.getX() <= (Window.getWINDOW_WIDTH() / 2)){
            indexTerrorist = 0;
            terroristPassLimit = true;
        }
        if (terrorist2.getX() <= (Window.getWINDOW_WIDTH() / 2)){
            indexTerrorist = 1;
            terroristPassLimit = true;
        }
        if (terrorist3.getX() <= (Window.getWINDOW_WIDTH() / 2)){
            indexTerrorist = 2;
            terroristPassLimit = true;
        }
        if (terrorist4.getX() <= (Window.getWINDOW_WIDTH() / 2)){
            indexTerrorist = 3;
            terroristPassLimit = true;
        }
        if (terrorist5.getX() <= (Window.getWINDOW_WIDTH() / 2)){
            indexTerrorist = 4;
            terroristPassLimit = true;
        }

//        if (soldier1.getX() <= (Window.getWINDOW_WIDTH() / 2) - 32){
//            soldier1PassLimit = true;
//        }
//        if (soldier2.getX() <= (Window.getWINDOW_WIDTH() / 2) - 32){
//            soldier2PassLimit = true;
//        }


    }


    public void safetyDistance(){

        if (this.difficultLevel == easy) {

            if (soldier1.catchTheSoldier().intersects(terrorist1.distanceRectangle()) || soldier1.catchTheSoldier().intersects(terrorist2.distanceRectangle()) || soldier1.catchTheSoldier().intersects(terrorist3.distanceRectangle()) || soldier1.catchTheSoldier().intersects(terrorist4.distanceRectangle()) || soldier1.catchTheSoldier().intersects(terrorist5.distanceRectangle())) {
                soldier1.setMove(false);
            } else {
                soldier1.setMove(true);
            }

            if (soldier2.catchTheSoldier().intersects(terrorist1.distanceRectangle()) || soldier2.catchTheSoldier().intersects(terrorist2.distanceRectangle()) || soldier2.catchTheSoldier().intersects(terrorist3.distanceRectangle()) || soldier2.catchTheSoldier().intersects(terrorist4.distanceRectangle()) || soldier2.catchTheSoldier().intersects(terrorist5.distanceRectangle())) {
                soldier2.setMove(false);
            } else {
                soldier2.setMove(true);
            }
        } else {

            if (soldier1.catchTheSoldier().intersects(terrorist1.distanceRectangleDouble()) || soldier1.catchTheSoldier().intersects(terrorist2.distanceRectangleDouble()) || soldier1.catchTheSoldier().intersects(terrorist3.distanceRectangleDouble()) || soldier1.catchTheSoldier().intersects(terrorist4.distanceRectangleDouble()) || soldier1.catchTheSoldier().intersects(terrorist5.distanceRectangleDouble())) {
                soldier1.setMove(false);
            } else {
                soldier1.setMove(true);
            }

            if (soldier2.catchTheSoldier().intersects(terrorist1.distanceRectangleDouble()) || soldier2.catchTheSoldier().intersects(terrorist2.distanceRectangleDouble()) || soldier2.catchTheSoldier().intersects(terrorist3.distanceRectangleDouble()) || soldier2.catchTheSoldier().intersects(terrorist4.distanceRectangleDouble()) || soldier2.catchTheSoldier().intersects(terrorist5.distanceRectangleDouble())) {
                soldier2.setMove(false);
            } else {
                soldier2.setMove(true);
            }
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
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (this.tank.getX() > 66){
                if (!isFire) {
                    this.fireSound.playFireSound();
                }
                this.isFire = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void showWindow (){
        this.setVisible(true);
    }

}

