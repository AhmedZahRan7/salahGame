package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.MainGui.MainFrame;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main {
	
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");
		/* -------------------------------------------------------------------- */
		/* using default background color */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Gold(400, 400));
		
		/* -------------------------------------------------------------------- */
		/* using background color at the game */
	//	GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Ball(700, 400), Color.YELLOW);
		
		/* -------------------------------------------------------------------- */
		/* controlling the behavior of the close button
		 * alternatively, you can use JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Bubles(600, 600), JFrame.EXIT_ON_CLOSE);

//		/* using menus in the game */
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem  exitMenuItem = new JMenuItem("Exit");
//		exitMenuItem.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		menu.add(exitMenuItem);
//		menuBar.add(menu);
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.StarWar(800, 600), menuBar, Color.BLACK);

		/* -------------------------------------------------------------------- */
		/* allow pause, resume, and restart multiple worlds in the same frame */
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem newMenuItem = new JMenuItem("New");
//		JMenuItem pauseMenuItem = new JMenuItem("Pause");
//		JMenuItem resumeMenuItem = new JMenuItem("Resume");
//		menu.add(newMenuItem);
//		menu.addSeparator();
//		menu.add(pauseMenuItem);
//		menu.add(resumeMenuItem);
//		menuBar.add(menu);
//		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Space(400, 700), menuBar, Color.BLACK);
//		newMenuItem.addActionListener(new ActionListener() {
//		@Override public void actionPerformed(ActionEvent e) {
//				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.sample.world.Space(400, 700));
//			}
//		});
//		pauseMenuItem.addActionListener(new ActionListener() {
//		@Override public void actionPerformed(ActionEvent e) {
//				gameController.pause();
//			}
//		});
//		resumeMenuItem.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				gameController.resume();
//			}
//		});


//
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem newMenuItem = new JMenuItem("New");
//		JMenuItem pauseMenuItem = new JMenuItem("Pause");
//		JMenuItem resumeMenuItem = new JMenuItem("Resume");
//		menu.add(newMenuItem);
//		menu.addSeparator();
//		menu.add(pauseMenuItem);
//		menu.add(resumeMenuItem);
//		menuBar.add(menu);
//		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.WorldLevel1(1350, 700), menuBar, Color.WHITE);
		SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
//		System.out.println(File.separator);
//		String earlyPath= ".src.eg.edu.alexu.csd.oop.game.".replaceAll("\\.","\\"+ File.separator);;
//		System.out.println(earlyPath);
//		String bip = "sounds"+System.getProperty("file.separator")+"goal"+"4"+".mp3";
//		Media hit = new Media(new File(bip).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(hit);
//		mediaPlayer.play();


//
//		Clip clip = AudioSystem.getClip();
//        clip.open(AudioSystem.getAudioInputStream(new File("sounds"+System.getProperty("file.separator")+"goal"+"4"+".mp3")));
//        clip.start();
//        Thread.sleep(clip.getMicrosecondLength()/1000);
//		AudioInputStream inputStream = AudioSystem.getAudioInputStream(
//				Main.class.getResourceAsStream("sounds"+System.getProperty("file.separator")+"goal"+"4"+".mp3"));
//		clip.open(inputStream);
//		clip.start();
//		InputStream music=new FileInputStream(new File("sounds"+System.getProperty("file.separator")+"goal"+"4"+".mp3"));
//		AudioInputStream  audioSystem=new AudioInputStream(music);

		//new SoundClipTest();

	}

	}



	


