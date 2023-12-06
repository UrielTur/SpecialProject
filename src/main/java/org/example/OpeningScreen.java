package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OpeningScreen extends JPanel{
    private JLabel header;
    private JButton enter;
    private final int xHeader = 305;
    private final int yHeader = 150;
    private final int widthHeader = 550;
    private final int heightHeader = 70;
    private final int xEnter = 460;
    private final int yEnter = 510;
    private final int widthEnter = 120;
    private final int heightEnter = 80;
    private final ImageIcon background = new ImageIcon("src/main/resources/OpeningScreen background.jpeg");
    private final int xOfBackground = 0;
    private final int yOfBackground = 0;


    public OpeningScreen(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.header = new JLabel("TANKS WAR");
        this.header.setBounds(xHeader,yHeader,widthHeader,heightHeader);
        this.header.setFont(new Font("Arial" , Font.BOLD, 70));
        this.header.setForeground(Color.white);


        this.enter = new JButton("Enter");
        this.enter.setBounds(xEnter, yEnter, widthEnter, heightEnter);
        this.enter.setFont(new Font("Arial",Font.BOLD, 35));
        this.enter.setForeground(Color.BLACK);
        this.enter.setBackground(Color.gray);
        this.enter.setFocusable(true);
        this.enter.setFocusPainted(false);//מבטל את המסגרת התמידית מסביב לטקסט
        this.enter.setBorder(null);//מבטל את המסגרת המופיעה כשהעכבר על הכפתור

        this.enter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    enter.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.add(header);
        this.add(enter);
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.background.paintIcon(null,graphics,this.xOfBackground, this.yOfBackground);
    }


    public JButton getEnter() {
        return enter;
    }


}
