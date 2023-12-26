package org.example;

import javax.swing.*;

public class Window extends JFrame {
    private static final short WINDOW_WIDTH = 1050;
    private static final short WINDOW_HEIGHT = 700;

    private final OpeningScreen openingScreen;
    private final OptionsScreen optionsScreen;
    private final InstructionsScreen instructionsScreen;
    private final GameScene gameScene;
    private final ClickSound clickSound;
    private final LobbyBackgroundSound lobbyBackgroundSound;
    private final SceneBackgroundSound sceneBackgroundSound;
    private final Garage garage;



    public Window() {

        this.lobbyBackgroundSound = new LobbyBackgroundSound();
        this.lobbyBackgroundSound.playWarSound();

        this.sceneBackgroundSound = new SceneBackgroundSound();




        this.clickSound = new ClickSound();
        this.clickSound.playClickAudio();

        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tanks War");
        this.setLocationRelativeTo(null);


        this.openingScreen = new OpeningScreen();
        this.add(openingScreen);

        this.instructionsScreen = new InstructionsScreen();
        this.add(instructionsScreen);

        this.garage = new Garage();
        this.add(garage);


        this.optionsScreen = new OptionsScreen();
        this.add(optionsScreen);
        optionsScreen.setVisible(false);

        this.gameScene = new GameScene();
//        this.add(gameScene);
//        gameScene.setVisible(false);



        this.openingScreen.getEnter().addActionListener(e -> {
            this.clickSound.playClickAudio();
            this.openingScreen.setVisible(false);
            this.optionsScreen.showWindow();
        });


        this.optionsScreen.getGameScene().addActionListener(e -> {
            this.add(gameScene);
            this.gameScene.showWindow();
            this.lobbyBackgroundSound.stopPlay();
            this.sceneBackgroundSound.playWarSound();
            this.clickSound.playClickAudio();
            this.optionsScreen.setVisible(false);
            this.gameScene.setFocusable(true);
            this.gameScene.requestFocus();
        });


        this.optionsScreen.getGarage().addActionListener(e -> {
            this.clickSound.playClickAudio();
            this.optionsScreen.setVisible(false);
        });


        this.optionsScreen.getInstructions().addActionListener(e -> {
            this.clickSound.playClickAudio();
            this.optionsScreen.setVisible(false);
            this.instructionsScreen.showWindow();
        });

        this.instructionsScreen.getBackButton().addActionListener(e -> {
            this.clickSound.playClickAudio();
            this.instructionsScreen.setVisible(false);
            this.optionsScreen.setVisible(true);

        });

        this.optionsScreen.getGarage().addActionListener(e -> {
            this.clickSound.playClickAudio();
            this.optionsScreen.setVisible(false);
            this.garage.showWindow();
        });

        this.garage.getBackButton().addActionListener(e -> {
            this.clickSound.playClickAudio();
            this.garage.setVisible(false);
            this.optionsScreen.setVisible(true);

        });


    }

    public void showWindow(){
        setVisible(true);

    }


    public static short getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public static short getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

}
