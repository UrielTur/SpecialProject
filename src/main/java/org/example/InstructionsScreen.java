package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsScreen extends JPanel {
    private final Image background = new ImageIcon("src/main/resources/Instruction background.png").getImage();
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
        graphics.drawImage(this.background,  this.xOfBackground, this.yOfBackground ,background.getWidth(null) ,background.getHeight(null),null);
    }

    public JButton getBackButton() {
        return this.backButton;
    }
    public void showWindow (){
        this.setVisible(true);
    }
}
