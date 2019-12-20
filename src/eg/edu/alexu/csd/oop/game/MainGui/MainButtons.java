package eg.edu.alexu.csd.oop.game.MainGui;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class MainButtons extends JButton {
	/** label of the Buttons */
	private String label = null;
	/** width of the button */
	private final int width = 300;
	/** height of the button */
	private final int height = 10;

	public MainButtons() {
		this("Button");
	}

	public MainButtons(String label) {
		super.setLabel(label);
		super.setPreferredSize(new Dimension(width, height));
		super.setForeground(Color.WHITE);
		super.setFont(new Font("Dialog", Font.BOLD, 16));
		super.setOpaque(false);
		super.setContentAreaFilled(false);
		super.setBorderPainted(false);
	}

	public void setLabel(String label) {
		super.setLabel(label);
	}

	public String getLabel() {
		return super.getLabel();
	}
}