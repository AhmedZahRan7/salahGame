package eg.edu.alexu.csd.oop.game.sample.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public class CrossObject implements GameObject{
	public static final int SPRITE_WIDTH = 20;
	private static final int MAX_MSTATE = 25;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	boolean visible;

	public CrossObject(int posX, int posY, Color color){
		this.x = posX;
		this.y = posY;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		for (int i = 0; i < spriteImages.length; i++) {
			spriteImages[i] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH,	BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = spriteImages[i].createGraphics();
			g2.setColor(color);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			double theta = i * Math.PI / (2 * spriteImages.length);
			double x = SPRITE_WIDTH * Math.abs(Math.cos(theta)) / 2.0;
			double y = SPRITE_WIDTH * Math.abs(Math.sin(theta)) / 2.0;
			int x1 = (int) ((SPRITE_WIDTH / 2.0) - x);
			int y1 = (int) ((SPRITE_WIDTH / 2.0) - y);
			int x2 = (int) ((SPRITE_WIDTH / 2.0) + x);
			int y2 = (int) ((SPRITE_WIDTH / 2.0) + y);
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(x1, y1, x2, y2);
			g2.drawLine(y1, x2, y2, x1);
			g2.dispose();
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return SPRITE_WIDTH;
	}

	@Override
	public int getHeight() {
		return SPRITE_WIDTH;
	}

	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	@Override
	public boolean isVisible() {
		return visible;
	}

}
