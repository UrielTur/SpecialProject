package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SceneBackgroundSound {

        private AudioInputStream audioInputStream;
        private Clip clip;
        private boolean isPlay = false;



        public void playWarSound() {
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/WarBackgroundSound.wav").getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);


                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            } catch(Exception ex){
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }

        }

        public void setPlay(boolean play) {
            isPlay = play;
        }

        public void stopPlay() {
            this.clip.close();
        }
    }


