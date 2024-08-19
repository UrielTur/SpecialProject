package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {
    private Tank tank;
    private final Helicopter helicopter;
    private final Rocket rocket;
    private Terrorist[] terrorists;
    private final Soldier[] soldiers;
    private final HeartsOfLife heartsOfLife;
    private final Hits hits;
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
    private boolean soldierPassLimit = false;
    private boolean soldier1PassLimit = false;
    private boolean soldier2PassLimit = false;
    private byte indexTerrorist = 0;
    private byte indexSoldier = 0;
    private short counterOfSurvivors = 0;
    private short counterOfHits = 0;
    private byte counterOfMisses = 0;
    private final byte easy = 10;
    private final byte medium = 6;
    private final byte hard = 4;
    private byte difficultLevel = easy;
    private final JLabel labelHits;
    private final JLabel labelSurvivors;
    private final FireSound fireSound;
    private GameOverScreen gameOverScreen;


    public GameScene(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);

        this.fireSound = new FireSound();
        this.gameOverScreen =new GameOverScreen();

        this.tank = new Tank();
        this.rocket = new Rocket();
        this.helicopter = new Helicopter();

        this.terrorists = new Terrorist[5];
        this.soldiers = new Soldier[2];
            for (int i = 0; i < terrorists.length; i++) {
                this.terrorists[i] = new Terrorist(Window.getWINDOW_WIDTH() + (40 * (i + 1)), Window.getWINDOW_WIDTH() * 6);
                this.terrorists[i].start();
            }

            for (int i = 0; i < soldiers.length; i++) {
                this.soldiers[i] = new Soldier((short) (Window.getWINDOW_WIDTH() + 200), (short) (Window.getWINDOW_WIDTH() * 6));
                this.soldiers[i].start();
            }

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

        this.gameOverScreen.setVisible(false);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);

        mainGameLoop();
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.background1.paintIcon(null,graphics, (int) this.xOfBackground1, this.yOfBackground1);
        this.background2.paintIcon(null,graphics, (int) this.xOfBackground2, this.yOfBackground2);
        this.helicopter.paintHelicopter(graphics);

        for (int i = 0; i < 5; i++) {
            this.terrorists[i].paintTerrorist(graphics);
            if (i < 2){
                this.soldiers[i].paintSoldier(graphics);
            }
        }

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
                    moveBackground();
                    checkCollision();
                    safetyDistance();
                    checkPassLimit();
                    for (int i = 0; i < terrorists.length; i++) {
                        terrorists[i].setMove(true);
                        if(i < 2){
                            soldiers[i].setMove(true);
                        }
                    }
                }
                if (isFire){
                    this.rocket.fire();
                }
                if (terroristHasCollision || soldierHasCollision || this.rocket.getX() > Window.getWINDOW_WIDTH()){
                    this.rocket.setX((short) 350);
                    this.isFire = false;
                }
                if (soldierHasCollision){
                    soldiers[indexSoldier].dead((short) (Window.getWINDOW_WIDTH()+250), (short) (Window.getWINDOW_WIDTH()*5));
                    this.counterOfMisses++;
                    this.soldierHasCollision = false;
                }
                if (terroristHasCollision){
                    terrorists[indexTerrorist].dead((short) (Window.getWINDOW_WIDTH()+250), (short) (Window.getWINDOW_WIDTH()*5));
                    this.counterOfHits++;
                    this.terroristHasCollision = false;
                }
                if (terroristPassLimit){
                    terrorists[indexTerrorist].dead((short) (Window.getWINDOW_WIDTH()+250), (short) (Window.getWINDOW_WIDTH()*5));
                    this.counterOfMisses++;
                    this.terroristPassLimit = false;
                }
                if (soldier1PassLimit) {
                    soldiers[0].moveUp((byte) 2);
                    if (soldiers[0].getY() <= Window.getWINDOW_HEIGHT()/3) {
                        soldiers[0].dead((short) (Window.getWINDOW_WIDTH() + 250), (short) (Window.getWINDOW_WIDTH()*5));
                        this.counterOfSurvivors++;
                        this.soldier1PassLimit = false;
                    }
                }
                if (soldier2PassLimit) {
                    soldiers[1].moveUp((byte) 2);
                    if (soldiers[1].getY() <= Window.getWINDOW_HEIGHT()/3) {
                        soldiers[1].dead((short) (Window.getWINDOW_WIDTH() + 250), (short) (Window.getWINDOW_WIDTH()*5));
                        this.counterOfSurvivors++;
                        this.soldier2PassLimit = false;
                    }
                }
                if (counterOfMisses >= 1){
                    heartsOfLife.hideHeart3();
                }
                if (counterOfMisses >= 2) {
                    heartsOfLife.hideHeart2();
                }
                if (counterOfMisses >= 3) {
                    heartsOfLife.hideHeart1();
                }
                if (this.counterOfHits > 15 &&  this.counterOfHits <= 30) {
                    this.difficultLevel = medium;
                    sleepSpeed(medium);
                } else if (this.counterOfHits > 30) {
                    this.difficultLevel = hard;
                    sleepSpeed(hard);
                }
                try {
                    Thread.sleep(this.difficultLevel);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < terrorists.length; i++) {
                terrorists[i].setStillAlive(false);

                if (i < 2){
                    soldiers[i].setStillAlive(false);
                }
            }

            this.setVisible(false);
            this.gameOverScreen.showWindow();
            this.gameOverScreen.setFocusable(true);
        }).start();
    }

    public void moveBackground() {
        this.xOfBackground1 -= 1;
        this.xOfBackground2 -= 1;
    }


    public void sleepSpeed(byte speedLevel){
        for (int i = 0; i < terrorists.length; i++) {
            terrorists[i].setDifficultLevel(speedLevel);
            if (i < 2){
                soldiers[i].setDifficultLevel(speedLevel);
            }
        }
    }


    public void checkCollision() {

        for (int i = 0; i < terrorists.length; i++) {
            if (terrorists[i].catchTheTerrorist().intersects(this.rocket.calculateRectangle())) {
                terroristHasCollision = true;
                indexTerrorist = (byte) i;
            }
            if (i < 2) {
                if (soldiers[i].catchTheSoldier().intersects(this.rocket.calculateRectangle())) {
                    soldierHasCollision = true;
                    indexSoldier = (byte) i;
                }
            }
        }
    }


    public void checkPassLimit() {
        for (int i = 0; i < terrorists.length; i++) {
            if (terrorists[i].getX() <= (Window.getWINDOW_WIDTH() / 2)) {
                indexTerrorist = (byte) i;
                terroristPassLimit = true;
            }
            if (i < 2) {
                if (soldiers[i].getX() <= (Window.getWINDOW_WIDTH() / 2)) {
                    if (i == 0) {
                        soldier1PassLimit = true;
                    } else {
                        soldier2PassLimit = true;
                    }
                }
            }
        }
    }


    public void safetyDistance(){
        for (int i = 0; i < soldiers.length; i++) {
            if (this.difficultLevel == easy) {
                if (soldiers[i].catchTheSoldier().intersects(terrorists[0].distanceRectangle()) || soldiers[i].catchTheSoldier().intersects(terrorists[1].distanceRectangle()) || soldiers[i].catchTheSoldier().intersects(terrorists[2].distanceRectangle()) || soldiers[i].catchTheSoldier().intersects(terrorists[3].distanceRectangle()) || soldiers[i].catchTheSoldier().intersects(terrorists[4].distanceRectangle())) {
                    soldiers[i].setMove(false);
                    System.out.println(i + " easy");
                } else {
                    soldiers[i].setMove(true);
                }
            }else if (this.difficultLevel == medium){
                if (soldiers[i].catchTheSoldier().intersects(terrorists[0].distanceRectangleDouble()) || soldiers[i].catchTheSoldier().intersects(terrorists[1].distanceRectangleDouble()) || soldiers[i].catchTheSoldier().intersects(terrorists[2].distanceRectangleDouble()) || soldiers[i].catchTheSoldier().intersects(terrorists[3].distanceRectangleDouble()) || soldiers[i].catchTheSoldier().intersects(terrorists[4].distanceRectangleDouble())) {
                    soldiers[i].setMove(false);
                    System.out.println(i + " medium");
                } else {
                    soldiers[i].setMove(true);
                }
            }else if (this.difficultLevel == hard){
                if (soldiers[i].catchTheSoldier().intersects(terrorists[0].distanceRectangleTriple()) || soldiers[i].catchTheSoldier().intersects(terrorists[1].distanceRectangleTriple()) || soldiers[i].catchTheSoldier().intersects(terrorists[2].distanceRectangleTriple()) || soldiers[i].catchTheSoldier().intersects(terrorists[3].distanceRectangleTriple()) || soldiers[i].catchTheSoldier().intersects(terrorists[4].distanceRectangleTriple())) {
                    soldiers[i].setMove(false);
                    System.out.println(i + " hard");
                } else {
                    soldiers[i].setMove(true);
                }
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

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }

    public Tank getTank() {
        return tank;
    }


    public void showWindow (){
        this.setVisible(true);
    }
    public void restartSettings(){
        counterOfHits = 0;
        counterOfMisses = 0;
        counterOfSurvivors = 0;
        this.tank.setX((byte) 0);

        for (int i = 0; i < terrorists.length; i++) {
            terrorists[i].resetTerroristsLoop();
            if (i < 2){
                soldiers[i].resetSoldiersLoop();
            }
        }
    }
}

