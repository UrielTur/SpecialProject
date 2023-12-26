package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Garage extends JPanel {
    private final Image background = new ImageIcon("src/main/resources/Tank-Garage.png").getImage();
    private final byte xOfBackground = 0;
    private final byte yOfBackground = 0;
    private final JButton backButton;
    private final JButton tank1;
    private final JButton tank2;
    private final JButton tank3;
    private final ImageIcon tankIcon = new ImageIcon("src/main/resources/Tank.png");

    public Garage() {
        this.setVisible(false);
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
        this.tank1 = new JButton(tankIcon);
        this.tank1.setBounds(150, 350, tankIcon.getIconWidth(), tankIcon.getIconHeight());// מגדיר מיקום וגודל לכפתור
        this.tank1.setBorderPainted(false);
        this.tank1.setContentAreaFilled(false);

        this.add(tank1);

//        this.tank1.setIcon((Icon) tankIcon);

//        try {
//            tank1.setIcon(new ImageIcon("src/main/resources/Tank.png"));
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }






        this.tank2 = new JButton();
        this.tank3 = new JButton();


        this.backButton = new JButton("Back");
        this.backButton.setBounds(10, 600, 100, 50); // מגדיר מיקום וגודל לכפתור
        this.backButton.setFont(new Font("Arial" , Font.BOLD , 20));
        add(this.backButton);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
//        this.background.paintIcon(null,graphics,this.xOfBackground, this.yOfBackground);
        graphics.drawImage(this.background, this.xOfBackground, this.yOfBackground, Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT(),null);

//        graphics.drawImage(this.tankIcon,150, 350, tankIcon.getWidth(null), tankIcon.getHeight(null) ,null);



    }

    public JButton getBackButton() {
        return this.backButton;
    }
    public void showWindow (){
        this.setVisible(true);
    }

    public JButton getTank1() {
        return tank1;
    }

    public JButton getTank2() {
        return tank2;
    }

    public JButton getTank3() {
        return tank3;
    }
}

