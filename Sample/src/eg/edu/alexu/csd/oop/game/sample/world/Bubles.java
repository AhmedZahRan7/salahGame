package eg.edu.alexu.csd.oop.game.sample.world;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.object.BarObject;
import eg.edu.alexu.csd.oop.game.sample.object.BubleObject;
import eg.edu.alexu.csd.oop.game.sample.object.CrossObject;

public class Bubles implements World{
	private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	public Bubles(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
		// control objects (hero)
		for(int i=1; i<5; i++)
			control.add(new BarObject(screenWidth*i*2/11, screenHeight*7/8, 40, false, Color.BLACK));
		// moving objects (enemy)
		for(Color color : new Color[] { Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.GRAY })
			moving.add(new BubleObject((int)(Math.random() * screenWidth/2), (int)(Math.random() * screenHeight/2), Math.random()>0.5, Math.random()>0.5, color));
		// constants objects (enemy)
		for(int i=0; i<10; i++)
			constant.add(new CrossObject((int)(screenWidth*Math.random()), (int)(screenHeight*Math.random()), Color.RED));
	}
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
		for(GameObject m : moving){			// change ball position
			BubleObject ball = (BubleObject)m;
			ball.setX(ball.getX() + (ball.isLeft() ? 1 : -1));
			ball.setY(ball.getY() + (ball.isTop() ? 1 : -1));
			boolean hitBar = false;
			if(!timeout)
				for(GameObject bar : control)
					if(intersect(bar, ball)){	// hit the ball 
						score++;	// acquire score
						hitBar = true;
						if(!ball.isTop())
							ball.setY(Math.min(getHeight() - 2 * ball.getHeight(), ball.getY() + ball.getHeight()/2));
						else
							ball.setY(Math.max(0, ball.getY() - ball.getHeight()/2));
					}
			if(hitBar || ball.getY() < 0 || ball.getY()+ball.getHeight()+20 > getHeight())
				ball.setTop(!ball.isTop());		// reverse vertical direction
			if(ball.getX() < 0 || ball.getX()+ball.getWidth() > getWidth())
				ball.setLeft(!ball.isLeft());	// reverse horizontal direction
		}
		for(GameObject bar : control)
			for(GameObject c : constant)
				if(intersect(bar, c))	// hit a cross
					score = Math.max(0, score-1);	// lose score
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
		return "Please Use Arrows To Move   |   Location = " + control.get(0).getX() + "," + control.get(0).getY() + "   |   Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
	}
}