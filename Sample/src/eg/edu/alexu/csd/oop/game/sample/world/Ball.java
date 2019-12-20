package eg.edu.alexu.csd.oop.game.sample.world;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.object.BallObject;
import eg.edu.alexu.csd.oop.game.sample.object.BarObject;

public class Ball implements World{
	private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
	private int score = 0;
	private long endTime, startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	public Ball(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
		for(int i=7; i>6; i--)			// bar object (hero)
			control.add(new BarObject(screenWidth*5/11, screenHeight*i/8, 90, true, Color.BLUE));
		for(int i=0; i<1; i++)			// balls objects (enemy)
			moving.add(new BallObject((int)(Math.random() * screenWidth/2), (int)(Math.random() * screenHeight/4 + screenHeight/2), Math.random()>0.5, Math.random()>0.5, Color.RED));
		for(int i=1; i<11; i++)			// constants objects (gold)
			for(int j=30; j<=90; j+=30)
				constant.add(new BarObject(width*i/11, j, 15, true, Color.GREEN));
	}
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override
	public boolean refresh() {
		boolean timeout = score==constant.size() || System.currentTimeMillis() - startTime > MAX_TIME; // finish all brackets or time end
		for(GameObject m : moving){			// change ball position
			BallObject ball = (BallObject)m;
			ball.setX(ball.getX() + (ball.isLeft() ? 1 : -1));
			ball.setY(ball.getY() + (ball.isTop() ? 1 : -1));
			boolean hitBar = false;
			if(!timeout){
				endTime = System.currentTimeMillis();
				for(GameObject c : constant)
					if(c.isVisible() && intersect(c, ball)){	// hit a bracket
						score ++;	// lose score				
						hitBar = true;
						((BarObject)c).setVisible(false);
					}
				for(GameObject bar : control)
					if(intersect(bar, ball)){	// hit the ball 
						hitBar = true;
						if(!ball.isTop())
							ball.setY(Math.min(getHeight() - 2 * ball.getHeight(), ball.getY() + ball.getHeight()/2));
						else
							ball.setY(Math.max(0, ball.getY() - ball.getHeight()/2));
					}
			}
			if(hitBar || ball.getY() < 0 || ball.getY()+ball.getHeight()+20 > getHeight())
				ball.setTop(!ball.isTop());		// reverse vertical direction
			if(ball.getX() < 0 || ball.getX()+ball.getWidth() > getWidth())
				ball.setLeft(!ball.isLeft());	// reverse horizontal direction
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
		return "Please Use Arrows To Move   |   Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (endTime-startTime))/1000);	// update status
	}
}