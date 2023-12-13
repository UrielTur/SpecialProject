package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsScreen extends JPanel {
    private final ImageIcon background = new ImageIcon(getClass().getResource("/Instruction background.png"));
    private final byte xOfBackground = 0;
    private final byte yOfBackground = 0;
    private final JButton backButton;

    public InstructionsScreen () {
        this.setVisible(false);
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);


        this.backButton = new JButton("Back");
        this.backButton.setBounds(10, 600, 100, 50); // מגדיר מיקום וגודל לכפתור
        this.backButton.setFont(new Font("Arial" , Font.BOLD , 20));
        add(this.backButton);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.background.paintIcon(null,graphics,this.xOfBackground, this.yOfBackground);
    }

    public JButton getBackButton() {
        return this.backButton;
    }
    public void showWindow (){
        this.setVisible(true);
    }
}
