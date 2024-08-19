package org.example;

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
    private final ImageIcon tankIcon1 = new ImageIcon("src/main/resources/Tank.png");
    private final ImageIcon tankIcon2 = new ImageIcon("src/main/resources/secondTank-min.png");
    private final ImageIcon tankIcon3 = new ImageIcon("src/main/resources/thirdTank-min.png");
    private final JLabel text;


    public Garage() {
        this.setVisible(false);
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
        this.text = new JLabel("Select your tank: ");
        this.text.setBounds(325, 110, 400, 120);
        this.text.setFont(new Font("Arial", Font.BOLD, 45));
        this.text.setForeground(Color.white);
        this.add(text);

        this.tankIcon1.setImage(this.tankIcon1.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        this.tank1 = new JButton(tankIcon1);
        this.tank1.setBounds(25, 440, 275, 100);// מגדיר מיקום וגודל לכפתור
        this.tank1.setContentAreaFilled(false);
        this.tank1.setBorder(null);
        this.add(tank1);

        this.tankIcon2.setImage(this.tankIcon2.getImage().getScaledInstance(320, 290, Image.SCALE_SMOOTH));
        this.tank2 = new JButton(tankIcon2);
        this.tank2.setBounds(350, 425, 320, 120);// מגדיר מיקום וגודל לכפתור
        this.tank2.setBorder(null);
        this.tank2.setContentAreaFilled(false);
        this.add(tank2);

        this.tankIcon3.setImage(this.tankIcon3.getImage().getScaledInstance(285, 170, Image.SCALE_SMOOTH));
        this.tank3 = new JButton(tankIcon3);
        this.tank3.setBounds(710, 415, 290, 130);// מגדיר מיקום וגודל לכפתור
        this.tank3.setBorder(null);
        this.tank3.setContentAreaFilled(false);
        this.add(tank3);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(10, 600, 100, 50); // מגדיר מיקום וגודל לכפתור
        this.backButton.setFont(new Font("Arial" , Font.BOLD , 20));
        add(this.backButton);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(this.background, this.xOfBackground, this.yOfBackground, Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT(),null);
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

