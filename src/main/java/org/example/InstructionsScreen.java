package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsScreen extends JPanel {
    private final ImageIcon background = new ImageIcon("src/main/resources/Instruction background.png");
    private final int xOfBackground = 0;
    private final int yOfBackground = 0;

    public InstructionsScreen () {
        this.setVisible(false);
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
//        this.setBackground(Color.BLUE);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.background.paintIcon(null,graphics,this.xOfBackground, this.yOfBackground);
    }
    public void showWindow (){
        this.setVisible(true);
    }
}
