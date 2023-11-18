package org.example;

import javax.swing.*;
import java.awt.*;

public class OpeningScreen extends JPanel {
    private JLabel header;



    private JButton enter;
    private final int xHeader = 350;
    private final int yHeader = 180;
    private final int widthHeader = 550;
    private final int heightHeader = 70;
    private final int xEnter = 440;
    private final int yEnter = 400;
    private final int widthEnter = 120;
    private final int heightEnter = 80;



    public OpeningScreen(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.header = new JLabel("TANKS WAR");
        this.header.setFont(new Font("Arial" , Font.ITALIC , 50));
        this.header.setBounds(xHeader,yHeader,widthHeader,heightHeader);
        this.enter = new JButton("enter");
        this.enter.setBounds(xEnter, yEnter, widthEnter, heightEnter);
        this.enter.setFont(new Font("Arial",Font.BOLD, 30));
        this.enter.setFocusable(false);

        this.add(header);
        this.add(enter);
    }


    public JButton getEnter() {
        return enter;
    }




}
