package eg.edu.alexu.csd.oop.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageObject implements GameObject, Cloneable {
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private boolean visible;
	private int type;
	private Boolean horizontal;
	private String path;
	//private GameObject test= ballFactory.getRandomBall(100,100);

	public ImageObject(int posX, int posY, BufferedImage spriteImages, Boolean horizontal , int type){
		this(posX, posY, spriteImages, type);
		this.horizontal = horizontal;
	}
	public ImageObject(int posX, int posY, String path, Boolean horizontal, int type){
		this(posX, posY, path, type);
		this.horizontal = horizontal;
	}
	
	public ImageObject(int posX, int posY,  BufferedImage spriteImages, int type){
		this.x = posX;
		this.y = posY;
		this.type = type;
		this.visible = true;

		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			//  ImageIO.read(getClass().getResourceAsStream(path))
			this.spriteImages[0] = spriteImages;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ImageObject(int posX, int posY,  String path, int type){
		this.x = posX;
		this.y = posY;
		this.type = type;
		this.visible = true;
		this.path = path;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			//  ImageIO.read(getClass().getResourceAsStream(path))
			this.spriteImages[0] =  ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
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
		if (horizontal) return;
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getPath(){
		return this.path;
	}
	public ImageObject clone(){
		// this method is only called to get clone for ball  to be set in cup so the horizontal is always  true
		return new ImageObject(this.x,this.y,spriteImages[0],true,this.type);
	}
	
}
