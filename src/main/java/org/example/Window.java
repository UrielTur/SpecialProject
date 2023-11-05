package org.example;

import javax.swing.*;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 1050;
    private static final int WINDOW_HEIGHT = 700;


    public Window() {

        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tanks War");
        this.setLocationRelativeTo(null);


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
