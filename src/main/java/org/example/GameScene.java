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
    private final Terrorist terrorist1;
    private final Terrorist terrorist2;
    private final Terrorist terrorist3;
    private final Terrorist terrorist4;
    private final Terrorist terrorist5;
    private final List<Terrorist> terrorists;

    private final Soldier soldier1;
    private final Soldier soldier2;
    private final List<Soldier> soldiers;
    private final HeartsOfLife heartsOfLife;
    private final Hits hits;


    private final OptionsScreen optionsScreen;
    private final ImageIcon background1 = new ImageIcon("src/main/resources/GameBackground.png");
    private final ImageIcon background2 = new ImageIcon("src/main/resources/OppositeBackground1.png");

    private int xOfBackground1 = 0;
    private int xOfBackground2 = ((Window.getWINDOW_WIDTH())*2);
    private final int yOfBackground1 = -35;
    private final int yOfBackground2 = -35;
    private boolean isFire = false;
    private boolean terroristHasCollision = false;
    private boolean terroristPassLimit = false;
    private boolean soldierHasCollision = false;
    private boolean soldier1PassLimit = false;
    private boolean soldier2PassLimit = false;

    private int indexTerrorist = 0;
    private int indexSoldier = 0;
    private int counterOfSurvivors = 0;
    private int counterOfHits = 0;
    private int counterOfMisses = 0;
    private int easy = 9;
    private int medium = 7;
    private int hard = 6;
    private int difficultLevel = easy;

    private JLabel labelHits;
    private JLabel labelSurvivors;




    public GameScene(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);

        this.optionsScreen = new OptionsScreen();
        this.tank = new Tank();
        this.rocket = new Rocket();
        this.helicopter = new Helicopter();


        this.terrorist1 = new Terrorist(Window.getWINDOW_WIDTH()+40,Window.getWINDOW_WIDTH()*6);
        this.terrorist2 = new Terrorist(Window.getWINDOW_WIDTH()+100,Window.getWINDOW_WIDTH()*6);
        this.terrorist3 = new Terrorist(Window.getWINDOW_WIDTH()+160,Window.getWINDOW_WIDTH()*6);
        this.terrorist4 = new Terrorist(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*6);
        this.terrorist5 = new Terrorist(Window.getWINDOW_WIDTH()+250,Window.getWINDOW_WIDTH()*6);
        
        this.soldier1 = new Soldier(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*6);
        this.soldier2 = new Soldier(Window.getWINDOW_WIDTH()+200,Window.getWINDOW_WIDTH()*6);



        this.terrorists = List.of(terrorist1, terrorist2, terrorist3, terrorist4, terrorist5);
        this.soldiers = List.of(soldier1, soldier2);

        this.labelHits = new JLabel(String.valueOf(counterOfHits));
        this.labelHits.setBounds(65,10,200,40);
        this.labelHits.setFont(new Font("Arial" , Font.BOLD, 30));
//        this.labelHits.setForeground(new Color(Color.black, true));
        this.add(labelHits);

        this.labelSurvivors = new JLabel("Survivors " + String.valueOf(counterOfSurvivors));
        this.labelSurvivors.setBounds(10,60,200,40);
        this.labelSurvivors.setFont(new Font("Arial" , Font.BOLD, 30));
//        this.labelSurvivors.setForeground(new Color(0xE011F909, true));
        this.add(labelSurvivors);



        this.heartsOfLife = new HeartsOfLife();
        this.hits = new Hits();

        
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.background1.paintIcon(null,graphics,this.xOfBackground1, this.yOfBackground1);
        this.background2.paintIcon(null,graphics,this.xOfBackground2, this.yOfBackground2);
        this.helicopter.paintHelicopter(graphics);

        this.terrorists.get(0).paintTerrorist(graphics);
        this.terrorists.get(1).paintTerrorist(graphics);
        this.terrorists.get(2).paintTerrorist(graphics);
        this.terrorists.get(3).paintTerrorist(graphics);
        this.terrorists.get(4).paintTerrorist(graphics);
        
        this.soldiers.get(0).paintSoldier(graphics);
        this.soldiers.get(1).paintSoldier(graphics);


        this.rocket.paintRocket(graphics);
        this.tank.paintTank(graphics);

        this.labelHits.setText(String.valueOf(counterOfHits));
        this.labelSurvivors.setText("Survivors " + String.valueOf(counterOfSurvivors));
        this.heartsOfLife.paintHearts(graphics);
        this.hits.paintHitsIcon(graphics);
    }


    public synchronized void mainGameLoop(){
        new Thread(() -> {

            while (counterOfMisses < 3){
                repaint();
                safetyDistance();

                if (this.xOfBackground1 <= -(Window.getWINDOW_WIDTH()*2)){
                    this.xOfBackground1 = (Window.getWINDOW_WIDTH()*2);
                } else if (this.xOfBackground2 <= -(Window.getWINDOW_WIDTH()*2)){
                    this.xOfBackground2 = (Window.getWINDOW_WIDTH()*2);
                }

                if (this.tank.getX() > 66){

                    this.terrorists.get(0).move(2);
                    this.terrorists.get(1).move(2);
                    this.terrorists.get(2).move(2);
                    this.terrorists.get(3).move(2);
                    this.terrorists.get(4).move(2);
                    
                    this.soldiers.get(0).move(2);
                    this.soldiers.get(1).move(2);

                    moveBackground();
                }


                if (isFire){
                    this.rocket.fire();
                }

                this.checkCollision();
                if (terroristHasCollision || soldierHasCollision || this.rocket.getX() > Window.getWINDOW_WIDTH()){
                    this.rocket.setX(350);
                    isFire = false;
                }
                if (soldierHasCollision){
                    soldiers.get(indexSoldier).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*5);
                    this.counterOfMisses++;
                    System.out.println(counterOfMisses);
                    soldierHasCollision = false;
                }
                if (terroristHasCollision){
                    terrorists.get(indexTerrorist).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*5);
                    this.counterOfHits++;
                    terroristHasCollision = false;
                }

                this.checkPassLimit();
                if (terroristPassLimit){
                    terrorists.get(indexTerrorist).dead(Window.getWINDOW_WIDTH()+250 , Window.getWINDOW_WIDTH()*5);
                    this.counterOfMisses++;
                    System.out.println(counterOfMisses);
                    this.terroristPassLimit = false;
                }



                if (soldier1PassLimit) {
                    soldiers.get(0).moveUp(2);
                    if (soldiers.get(0).getY() <= Window.getWINDOW_HEIGHT()/3) {
                        soldiers.get(0).dead(Window.getWINDOW_WIDTH() + 250, Window.getWINDOW_WIDTH()*5);
                        this.counterOfSurvivors++;
                        this.soldier1PassLimit = false;
                    }
                }
                if (soldier2PassLimit) {
                    soldiers.get(1).moveUp(2);
                    if (soldiers.get(1).getY() <= Window.getWINDOW_HEIGHT()/3) {
                        soldiers.get(1).dead(Window.getWINDOW_WIDTH() + 250, Window.getWINDOW_WIDTH()*5);
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

            }

        }).start();

    }


    public void moveBackground() {
        this.xOfBackground1 -= 1;
        this.xOfBackground2 -= 1;
    }


    public void checkCollision() {
        
        if (terrorist1.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            terroristHasCollision = true;
            indexTerrorist = 0;
        }
        if (terrorist2.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            terroristHasCollision = true;
            indexTerrorist = 1;
        }
        if (terrorist3.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            terroristHasCollision = true;
            indexTerrorist = 2;
        }
        if (terrorist4.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            terroristHasCollision = true;
            indexTerrorist = 3;
        }
        if (terrorist5.catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
            terroristHasCollision = true;
            indexTerrorist = 4;
        }

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

        if (terrorist1.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 0;
            terroristPassLimit = true;
        }
        if (terrorist2.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 1;
            terroristPassLimit = true;
        }
        if (terrorist3.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 2;
            terroristPassLimit = true;
        }
        if (terrorist4.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 3;
            terroristPassLimit = true;
        }
        if (terrorist5.getX() <= (Window.getWINDOW_WIDTH()/2)){
            indexTerrorist = 4;
            terroristPassLimit = true;
        }

        if (soldier1.getX() <= (Window.getWINDOW_WIDTH()/2) - 32){
            soldier1PassLimit = true;
        }
        if (soldier2.getX() <= (Window.getWINDOW_WIDTH()/2) - 32){
            soldier2PassLimit = true;
        }


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
                this.isFire = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

