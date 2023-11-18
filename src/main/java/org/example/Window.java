package org.example;

import javax.swing.*;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 1050;
    private static final int WINDOW_HEIGHT = 700;

    private final OpeningScreen openingScreen;
    private final OptionsScreen optionsScreen;
    private final GameScene gameScene;



    public Window() {

        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tanks War");
        this.setLocationRelativeTo(null);


        this.openingScreen = new OpeningScreen();
        this.add(openingScreen);


        this.optionsScreen = new OptionsScreen();
        this.add(optionsScreen);
        optionsScreen.setVisible(false);

        this.gameScene = new GameScene();
        this.add(gameScene);
        gameScene.setVisible(false);





        this.openingScreen.getEnter().addActionListener(e -> {
            this.openingScreen.setVisible(false);
            this.optionsScreen.setVisible(true);
        });

        this.optionsScreen.getGameScene().addActionListener(e -> {
            this.optionsScreen.setVisible(false);
            this.gameScene.setVisible(true);
            this.gameScene.mainGameLoop();
            this.gameScene.setFocusable(true);
            this.gameScene.requestFocus();
        });



        this.optionsScreen.getGarage().addActionListener(e -> {
            this.optionsScreen.setVisible(false);
        });


        this.optionsScreen.getInstructions().addActionListener(e -> {
            this.optionsScreen.setVisible(false);
        });


    }

    public void showWindow(){
        setVisible(true);

    }


    public static int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public static int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

}
