package org.example;

import javax.swing.*;
import java.awt.*;

public class OptionsScreen extends JPanel {


    private JButton gameScene;
    private JButton instructions;
    private JButton garage;
    private JButton exitFromGame;
    private final ImageIcon background;
    private final int xOfBackground = 0;
    private final int yOfBackground = 0;



    public OptionsScreen(){

        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);


        this.gameScene = new JButton("Start Play");
        this.gameScene.setBounds(10, 600, 150, 50); // מגדיר מיקום וגודל לכפתור
        this.gameScene.setFont(new Font("Arial" , Font.BOLD , 25));
        this.add(gameScene);


        this.garage = new JButton("Garage");
        this.garage.setBounds(190, 600, 150, 50); // מגדיר מיקום וגודל לכפתור
        this.garage.setFont(new Font("Arial" , Font.BOLD , 25));
        this.add(garage);

        this.instructions = new JButton("Instructions");
        this.instructions.setBounds(375, 600, 180, 50); // מגדיר מיקום וגודל לכפתור
        this.instructions.setFont(new Font("Arial" , Font.BOLD , 25));
        this.add(instructions);

        this.exitFromGame = new JButton("Exit");
        this.exitFromGame.setBounds(580, 600, 150, 50); // מגדיר מיקום וגודל לכפתור
        this.exitFromGame.setFont(new Font("Arial" , Font.BOLD , 25));
        this.exitFromGame.addActionListener(e -> {
            System.exit(0);
        });
        this.add(exitFromGame);

        this.background = new ImageIcon("src/main/resources/TankBackground.png");

    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.background.paintIcon(null,graphics,this.xOfBackground, this.yOfBackground);
    }

    public void showWindow (){
        this.setVisible(true);
    }

    public JButton getGameScene() {
        return gameScene;
    }

    public JButton getInstructions() {
        return instructions;
    }

    public JButton getGarage() {
        return garage;
    }

}
