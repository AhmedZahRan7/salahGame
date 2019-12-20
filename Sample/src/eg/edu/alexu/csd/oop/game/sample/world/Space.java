package eg.edu.alexu.csd.oop.game.sample.world;

import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.object.ImageObject;

public class Space implements World{
	private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	public Space(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
		// control objects (hero)
		control.add(new ImageObject(screenWidth/3, (int)(screenHeight*0.8), "/spaceship.png"));
		// moving objects (enemy)
		for(int i=0; i<10; i++)
			moving.add(new ImageObject((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight), "/star.png"));
		// constants objects (gold)
		for(int i=0; i<5; i++)
			constant.add(new ImageObject((int)(screenWidth*0.9*Math.random()), (int)(screenHeight*0.9*Math.random()), "/astronaut.png"));
	}
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
		GameObject spaceShip = control.get(0);
		// moving starts
		for(GameObject m : moving){
			m.setY((m.getY() + 1));
			if(m.getY()==getHeight()){
				// reuse the star in another position
				m.setY(-1 * (int)(Math.random() * getHeight()));
				m.setX((int)(Math.random() * getWidth()));	
			}
			m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));
			if(!timeout & intersect(m, spaceShip))
				score = Math.max(0, score-10);	// lose score
		}
		// collecting astronauts
		for(GameObject c : constant){
			if(c.isVisible()){
				if(intersect(c, spaceShip)){
					score++;	// get score
					((ImageObject)c).setVisible(false);
				}else if(Math.random() > 0.999)
					((ImageObject)c).setVisible(false);	// lost the astronauts
			}else{
				((ImageObject)c).setVisible(true);
				// reuse the astronaut in another position
				c.setX((int)(getWidth()*0.9*Math.random()));
				c.setY((int)(getHeight()*0.9*Math.random()));
			}
		}
		return !timeout;
	}
	@Override
	public int getSpeed() {	
		return 10; 
	}
	@Override
	public int getControlSpeed() {	
		return 10;  
	}
	@Override
	public List<GameObject> getConstantObjects() {	
		return constant;	
	}
	@Override
	public List<GameObject> getMovableObjects() {	
		return moving;	
	}
	@Override
	public List<GameObject> getControlableObjects() {	
		return control;	
	}
	@Override
	public int getWidth() {	
		return width; 
	}
	@Override
	public int getHeight() { 
		return height; 
	}
	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
	}
}