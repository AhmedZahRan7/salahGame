package eg.edu.alexu.csd.oop.game.sound;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundFactory  {
    public static void PlayGoal(){
        int number= (int)(Math.random() * 5);
        String filePath="sounds"+System.getProperty("file.separator")+"goal"+number+".wav";
        play(filePath);
    }
    private static void play(String path){
          new play(path).run();

    }

    public static void playFoulSound(){
        int number= (int) (Math.random() * 2);
        String filePath="sounds"+System.getProperty("file.separator")+"foul"+number+".wav";
        play(filePath);

    }


    private static class play extends Thread{
        private String Path;
        public play(String Path){
            this.Path=Path;
        }
        @Override
        public void run(){
            int number= (int)(Math.random() * 5);
            Clip clip = null;
            AudioInputStream audioInputStream=null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(Path).getAbsoluteFile());
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clip.loop(0);
        }
    }
}
