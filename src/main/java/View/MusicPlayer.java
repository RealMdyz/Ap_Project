package View;

import Models.Constant;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;
    private boolean loop;
    private FloatControl volumeControl;

    // Constructor
    public MusicPlayer(String filePath, boolean loop) {
        this.loop = loop;
        try {
            File file = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            setVolume(Constant.getSound());
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public MusicPlayer(){

    }

    // Method to start playing the music
    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    // Method to stop playing the music
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Method to close the resources
    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
    public static void playOnce(String filePath) {
        MusicPlayer player = new MusicPlayer(filePath, false);
        player.play();
    }
    public void setVolume(int volumePercent) {
        if (volumeControl != null) {
            // Ensure volume is within the valid range
            if (volumePercent < 0) {
                volumePercent = 0;
            } else if (volumePercent > 100) {
                volumePercent = 100;
            }
            // Convert volumePercent to a value between 0 and 1
            float volume = volumePercent / 100.0f;
            // Convert volume to dB
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);
        }
    }

}
