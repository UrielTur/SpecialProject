package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverScreen extends JPanel {

    private JLabel header;
    private JButton playAgain;
    private final short xHeader = 305;
    private final short yHeader = 150;
    private final short widthHeader = 550;
    private final byte heightHeader = 70;
    private final short xEnter = 430;
    private final short yEnter = 510;
    private final short widthEnter = 175;
    private final byte heightEnter = 80;
    private final Image background = new ImageIcon("src/main/resources/GameOverBackground.png").getImage();
    private final byte xOfBackground = 0;
    private final byte yOfBackground = 0;


    public GameOverScreen(){
        this.setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.header = new JLabel("GAME OVER");
        this.header.setBounds(xHeader,yHeader,widthHeader,heightHeader);
        this.header.setFont(new Font("Arial" , Font.BOLD, 70));
        this.header.setForeground(Color.black);

        this.playAgain = new JButton("Play again");
        this.playAgain.setBounds(xEnter, yEnter, widthEnter, heightEnter);
        this.playAgain.setFont(new Font("Arial",Font.BOLD, 35));
        this.playAgain.setForeground(Color.BLACK);
        this.playAgain.setBackground(Color.white);
        this.playAgain.setFocusable(true);
        this.playAgain.setFocusPainted(false);//מבטל את המסגרת התמידית מסביב לטקסט
        this.playAgain.setBorder(null);//מבטל את המסגרת המופיעה כשהעכבר על הכפתור

        this.playAgain.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    playAgain.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.add(header);
        this.add(playAgain);
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(this.background,  this.xOfBackground, this.yOfBackground,background.getWidth(null) ,background.getHeight(null),null);
    }


    public JButton getPlayAgain() {
        return playAgain;
    }

    public void showWindow(){
        setVisible(true);
    }

    public void hideWindow(){
        setVisible(false);
    }





}
