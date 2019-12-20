package eg.edu.alexu.csd.oop.game.MainGui;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameWorld;
import eg.edu.alexu.csd.oop.game.Levels.*;
import eg.edu.alexu.csd.oop.game.replay.Replayer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame {

	/** image put as icon */
	private ImageIcon exitIcon = null;
	/** instence of OptionButtons */
	private OptionsButtons options = null;
	/** instence of NewGamePanel */
	private NewGamePanel newGame = null;
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem pauseMenuItem = new JMenuItem("Pause");
	JMenuItem resumeMenuItem = new JMenuItem("Resume");

	public MainFrame() {
		super.setTitle("Circus Game");
		super.setSize(1200,700);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setIconImage(Toolkit.getDefaultToolkit().getImage("photos/circus.png"));
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		/*
		*	there is some magic on this area I don't really know how it works 
		*/
		super.setLayout(new BorderLayout());
		super.setContentPane(new JLabel(new ImageIcon("photos/bg.jpg")));
		super.setLayout(new FlowLayout(0,450,175));

		this.options = OptionsButtons.getInstance();
		this.newGame = NewGamePanel.getInstance();
		this.addSettingsToButtons();
		super.add(options);

		super.setSize(1199, 699);
		super.setSize(1200, 700);

		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitIcon = new ImageIcon("photos/icons8-question-mark-64.png");    
				int check = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure to exit?", "EXIT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, exitIcon);
				if(check == JOptionPane.YES_OPTION)
					MainFrame.this.dispose();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("The programm terminated succesfully...");
			}
		});
		menu.add(newMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);

	}

	private void addSettingsToButtons() {
		options.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				repaint();
				setLayout(null);
				add(newGame);
			}
		});

		newGame.getDoneButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField text = newGame.getTextField();
				String player = text.getText();
				if(player.equals("") || player.equals("Enter your name")) {
					text.setBackground(Color.RED);
					text.setText("PLEASE ENTER YOUR NAME");
					text.setEnabled(false);
				} else {
				setVisible(false);
				String level = newGame.getSelectedLevel();
				final GameEngine.GameController gameController;
				Level lev;

					if (level.equals("Level1"))
						lev=new Level1();
					else if (level.equals("Level2"))
						lev=new Level2();
					else if (level.equals("Level3"))
						lev=new Level3();
					else
						lev=new Level4();

					GameWorld world = new GameWorld(1350, 700,lev);
					GameEngine.start("MoooSalah",world, menuBar, Color.WHITE);
				}
			}
		});
	} 
}