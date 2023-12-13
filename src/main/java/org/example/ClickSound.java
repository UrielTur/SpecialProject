package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class ClickSound {


    private AudioInputStream audioInputStream;
    private Clip clip;



    public void playClickAudio() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource("/clickSound.wav").toURI()).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();

        } catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

    }

}

