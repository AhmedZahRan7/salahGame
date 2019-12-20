package eg.edu.alexu.csd.oop.game.sample.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public class BubleObject implements GameObject{
	public static final int SPRITE_WIDTH = 40;
	private static final int MAX_MSTATE = 100;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private boolean left;
	private boolean top;
	boolean visible;

	public BubleObject(int posX, int posY, boolean left, boolean top, Color color){
		this.x = posX;
		this.y = posY;
		this.left = left;
		this.top = top;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		int j = 0;
		for (int i = 0; i < spriteImages.length; i++) {
			spriteImages[i] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH,	BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = spriteImages[i].createGraphics();
			g2.setColor(color);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			j = Math.abs(spriteImages.length/2-i);
			double theta = j * Math.PI / (2 * spriteImages.length);
			double x = SPRITE_WIDTH * Math.abs(Math.cos(theta)) / 2.0;
			double y = SPRITE_WIDTH * Math.abs(Math.sin(theta)) / 2.0;
			int x1 = (int) ((SPRITE_WIDTH / 2.0) - x);
			int y1 = (int) ((SPRITE_WIDTH / 2.0) - y);
			int x2 = (int) ((SPRITE_WIDTH / 2.0) + x);
			int y2 = (int) ((SPRITE_WIDTH / 2.0) + y);
			g2.setStroke(new BasicStroke(5));
			g2.drawOval(x1+2, y1+2, x2-4, y2-4);
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

	@Override
	public boolean isVisible() {
		return visible;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

}
