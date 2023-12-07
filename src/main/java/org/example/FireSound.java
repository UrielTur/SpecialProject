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
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/fire-sound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);


            clip.start();


        } catch(Exception ex){
            System.out.println("Error with playing fire sound.");
            ex.printStackTrace();
        }

    }


}
