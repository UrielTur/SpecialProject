package org.example;

import javax.swing.*;
import java.awt.*;

public class OptionsScreen extends JPanel {


    private static JButton gameScene;
    private static JButton instructions;
    private static JButton garage;
    private JButton exitFromGame;


    public OptionsScreen(){

        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);


        gameScene = new JButton("Start Play");
        gameScene.setBounds(10, 600, 150, 50); // מגדיר מיקום וגודל לכפתור
        gameScene.setFont(new Font("Arial" , Font.BOLD , 25));
        add(gameScene);

        garage = new JButton("Garage");
        garage.setBounds(190, 600, 150, 50); // מגדיר מיקום וגודל לכפתור
        garage.setFont(new Font("Arial" , Font.BOLD , 25));
        add(garage);

        instructions = new JButton("Instructions");
        instructions.setBounds(375, 600, 180, 50); // מגדיר מיקום וגודל לכפתור
        instructions.setFont(new Font("Arial" , Font.BOLD , 25));
        add(instructions);

        exitFromGame = new JButton("Exit");
        exitFromGame.setBounds(580, 600, 150, 50); // מגדיר מיקום וגודל לכפתור
        exitFromGame.setFont(new Font("Arial" , Font.BOLD , 25));
        exitFromGame.addActionListener(e -> {
            System.exit(0);
        });
        add(exitFromGame);


    }



}
