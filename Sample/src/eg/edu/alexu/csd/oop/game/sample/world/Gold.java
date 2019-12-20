package eg.edu.alexu.csd.oop.game.sample.world;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.object.CrossObject;

public class Gold implements World{
	private int score;
	private final int width;
	private final int height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	public Gold(int screenWidth, int screenHeight) {
		score = 0;
		width = screenWidth;
		height = screenHeight;
		// control objects (hero)
		for(int i=4; i<7; i++)
			control.add(new CrossObject(screenWidth*i/10, screenHeight*1/16, Color.RED));
		// moving objects (enemy)
		for(int i=3; i<13; i++)
			moving.add(new CrossObject(screenWidth/2, screenHeight*i/16, Color.BLUE));
		// constants objects (gold)
		for(int i=0; i<20; i++)
			constant.add(new CrossObject((int)(screenWidth*0.9*Math.random()), (int)(screenHeight*0.9*Math.random()), Color.GREEN));
	}
	private boolean intersect(GameObject o1, GameObject o2){
		int delta = 10;
		return (Math.abs(o1.getX() - o2.getX()) <= delta) && (Math.abs(o1.getY() - o2.getY()) <= delta);
	}
	@Override
	public boolean refresh() {
		// randomly hide constant objects
		for(GameObject n : constant)
			if(n.isVisible() && Math.random() < 0.0002 )
				((CrossObject)n).setVisible(false);
		// change position of moving objects
		boolean direction = false;
		for(GameObject m : moving){
			m.setX((direction ? m.getX() + (int)(3 * Math.random()) : getWidth() + m.getX() - (int)(3 * Math.random())) % getWidth());	// move object
			direction = ! direction;
		}
		// check intersection with constant
		for(GameObject c : control)
			for(GameObject n : constant)
				if(n.isVisible() && intersect(c, n)){
					((CrossObject)n).setVisible(false);
					score++; 	// got score
				}
		// check intersection with moving objects
		for(GameObject c : control)
			for(GameObject m : moving)
				if(intersect(c, m))
					return false;	// game over (lose)
		// check if any constant object still visible
		boolean foundVisible = false;
		for(GameObject n : constant)
			foundVisible |= n.isVisible();
		if(!foundVisible)
			return false; // game ends (win)
		return true;
	}
	@Override
	public int getSpeed() {	
		return 20; 
	}
	@Override
	public int getControlSpeed() {	
		return 3;  
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
		return "Please Use Arrows To Move     |      Location = " + control.get(0).getX() + "," + control.get(0).getY() + "      |     Score = " + score;	// update status
	}
}