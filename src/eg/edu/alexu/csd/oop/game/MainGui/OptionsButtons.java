package eg.edu.alexu.csd.oop.game.MainGui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OptionsButtons extends JPanel {

	/** Instnse of OptionButtons */
	private volatile static OptionsButtons uniqueInstance = null;

	/** Button to start a new game */
	private MainButtons new_game = null;
	/** continue Button */
	private MainButtons continueX = null;
	/** options Button */
	private MainButtons scores = null;
	/** Help Button */
	private MainButtons help = null;
	/** Exit Button */
	private MainButtons exit = null;

	private OptionsButtons() {
		this.setMainPanel();
		this.setButtons();
		this.addActionsToButtons();
		this.addButtons();
	}

	/**
	*	due to singleton design pattern
	*   @return 
	*		instance of OptionButtons
	*/
	public static OptionsButtons getInstance() {
		if(uniqueInstance == null) {
			synchronized(OptionsButtons.class) {
				if(uniqueInstance == null)
					uniqueInstance = new OptionsButtons();
			}
		}
		return uniqueInstance;
	}

	private void setMainPanel() {
		super.setPreferredSize(new Dimension(300, 300));
		super.setLayout(new GridLayout(5,1,0,4));
		super.setBackground(new Color(0,0,0,150));
	}

	private void setButtons() {
		this.new_game = new MainButtons("NEW GAME");
		this.new_game.setForeground(Color.ORANGE);
		this.continueX = new MainButtons("CONTINUE");
		this.scores = new MainButtons("SCORES");
		this.help = new MainButtons("HELP");
		this.exit = new MainButtons("EXIT");
	}

	private void addActionsToButtons() {
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void addButtons() {		
		super.add(new_game);
		super.add(continueX);
		super.add(scores);
		super.add(help);
		super.add(exit);
	}

	public MainButtons getNewGameButton() {
		return this.new_game;
	}

	public MainButtons getScoresButton() {
		return this.scores;
	}
}