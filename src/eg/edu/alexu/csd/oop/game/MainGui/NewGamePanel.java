package eg.edu.alexu.csd.oop.game.MainGui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewGamePanel extends JPanel {

	/** salah Image */
	private BufferedImage img = null;
	/** player name */
	private JTextField player_name = null;
	/** level chooser */
	private JComboBox<String> levels = null;
	/** Button to start game */
	private MainButtons startGame = null;
	/** player name */
	private String player = null;
	/** selected level */
	private String selectedLevel = null;
	/** Panel for data */
	private JPanel collectionData = null;
	/** Default written */
	private final String TEXT = "Enter your name";
	/** items of comboBox */
	private final String[] items = {"Level1", "Level2", "Level3", "Level4"};
	/** instance of this panel */
	private volatile static NewGamePanel uniqueInstance = null;

	private NewGamePanel() {
		this.player_name = new JTextField(TEXT);
		this.levels = new JComboBox<String>(items);
		this.startGame = new MainButtons("DONE");
		this.collectionData = new JPanel();
		try {
			this.img = ImageIO.read(new File("photos/salah.png"));
		} catch(IOException ex){

		}

		super.setLayout(null);
		this.collectionDataSettings();
		this.setPlayerNameSettings();
		this.setLevelsSettings();
		this.setDoneSettings();
		this.setPanelSettings();

		super.add(collectionData);
		super.setBounds(300,50,600,475);
		super.setBackground(new Color(0,0,0,0 ));

	}

	public static NewGamePanel getInstance() {
		if(uniqueInstance == null) {
			synchronized(OptionsButtons.class) {
				if(uniqueInstance == null)
					uniqueInstance = new NewGamePanel();
			}
		}
		return uniqueInstance;
	}

	private void collectionDataSettings() {
		this.collectionData.setBounds(0,320,600,155);
		this.collectionData.setBackground(new Color(0,0,0,150));
		this.collectionData.setLayout(null);
	}

	private void setPlayerNameSettings() {
		this.player_name.setBounds(50,15,500,30);
		this.player_name.setHorizontalAlignment(JTextField.CENTER);
		this.player_name.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 16));
		this.player_name.setForeground(Color.BLACK);
		this.player_name.setEnabled(false);
		this.player_name.setBorder(null);
		this.player_name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				player_name.setEnabled(true);
				player_name.setBackground(Color.WHITE);
				player_name.setText("");
			}
		});
	}

	private void setLevelsSettings() {
		this.levels.setBounds(125,55,350,25);
		this.levels.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
		this.levels.setForeground(Color.BLACK);
	}

	private void setDoneSettings() {
		this.startGame.setBounds(175,90,250,50);
	}

	private void setPanelSettings() {
		this.collectionData.add(this.player_name);
		this.collectionData.add(this.levels);
		this.collectionData.add(this.startGame);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 150, 0, this);             
    }

    public MainButtons getDoneButton() {
    	return this.startGame;
    }

    public String getSelectedLevel() {
		return this.levels.getSelectedItem().toString();
	}

	public JTextField getTextField() {
		return this.player_name;
	}
}
