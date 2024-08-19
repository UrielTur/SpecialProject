package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class FireSound {
    private AudioInputStream audioInputStream;
    private Clip clip;


    public void playFireSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource("/fire-sound.wav").toURI()).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex){
            System.out.println("Error with playing fire sound.");
            ex.printStackTrace();
        }
    }
}
