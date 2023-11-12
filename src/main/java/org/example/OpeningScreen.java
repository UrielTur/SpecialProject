package org.example;

import javax.swing.*;

public class OpeningScreen extends JPanel {
    private JLabel header;



    private JButton start;
    private final int xHeader = 350;
    private final int yHeader = 180;
    private final int widthHeader = 550;
    private final int heightHeader = 70;
    private final int xStart = 450;
    private final int yStart = 400;
    private final int widthStart = 100;
    private final int heightStart = 70;



    public OpeningScreen(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.header = new JLabel("TANKS WAR");
        header.setBounds();
        this.start = new JButton();
    }


    public JButton getStart() {
        return start;
    }


}
