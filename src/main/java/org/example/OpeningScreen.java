package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OpeningScreen extends JPanel{
    private JLabel header;
    private JButton enter;
    private final short xHeader = 305;
    private final short yHeader = 150;
    private final short widthHeader = 550;
    private final byte heightHeader = 70;
    private final short xEnter = 460;
    private final short yEnter = 510;
    private final byte widthEnter = 120;
    private final byte heightEnter = 80;
    private final ImageIcon background = new ImageIcon(getClass().getResource("/OpeningScreen background.jpeg"));
    private final byte xOfBackground = 0;
    private final byte yOfBackground = 0;


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
