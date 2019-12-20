package eg.edu.alexu.csd.oop.game.sample.world;

import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.object.ImageObject;
import eg.edu.alexu.csd.oop.game.sample.object.SpaceFighterObject;
import eg.edu.alexu.csd.oop.game.sample.object.SpaceFighterObject.FireListener;

public class StarWar implements World, FireListener{
	private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
	private static int RIGHT_ROCKET = 123456;
	private static int LEFT_ROCKET  = 654321;
	private int score = 0;
	private boolean fireRocket = false;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	public StarWar(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
		// control objects (fighter)
		SpaceFighterObject fighter = new SpaceFighterObject(screenWidth/3, (int)(screenHeight*0.8), "/spaceship.png", this);
		control.add(fighter);
		control.add(new ImageObject(fighter.getX()+10, fighter.getY()+40, "/rocket.png", LEFT_ROCKET));
		control.add(new ImageObject(fighter.getX()+62, fighter.getY()+40, "/rocket.png", RIGHT_ROCKET));
		// moving objects (aliens)
		for(int i=0; i < 10; i++)
			moving.add(new ImageObject((int)(Math.random()*width), (int)(Math.random()*height/2), "/alien" + (int)(1 + Math.random() * 3)+ ".png"));
	}
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override 
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
		ImageObject fighter = (ImageObject)control.get(0);
		if(fireRocket){
			fireRocket = false;
			if(control.size()>1)
				moving.add(control.remove(1));	// release the rocket
		}
		for(GameObject o : moving.toArray(new GameObject[moving.size()])){
			int type = ((ImageObject)o).getType();
			if(type!=0){	// rocket
				if(o.getY()<-50){
					o.setX(fighter.getX()+(type==LEFT_ROCKET ? 10 : 62));
					o.setY(fighter.getY()+40);
					control.add(o);
					moving.remove(o);
				}else
					o.setY(o.getY() - 10);
				// check rocket intersection with aliens
				for(GameObject o2 : moving)
					if(o != o2){
						if(o2.isVisible()){
							if(intersect(o, o2)){
								((ImageObject)o2).setVisible(false);
								score++;	// gain score
							}
						}else{
							// reuse the alien in another position
							o2.setX((int)(Math.random()*width));
							o2.setY((int)(Math.random()*height/3));
							((ImageObject)o2).setVisible(true);
						}
					}
			}else{
				o.setY((o.getY() + 1));
				if(o.getY()==getHeight()){
					// reuse the alien in another position
					o.setY(-1 * (int)(Math.random() * getHeight()));
					o.setX((int)(Math.random() * getWidth()));	
				}
				o.setX(o.getX() + (Math.random() > 0.5 ? 2 : -2));
				if(!timeout & o.isVisible() && intersect(o, fighter))
					score = Math.max(0, score-10);	// lose score
			}
		}
		return !timeout;
	}
	@Override public void fire() {	fireRocket = true;	}
	@Override public int getSpeed() 		{	return 10;	}
	@Override public int getControlSpeed() 	{	return 20;	}
	@Override public List<GameObject> getConstantObjects() 	{	return constant;	}
	@Override public List<GameObject> getMovableObjects() 	{	return moving;		}
	@Override public List<GameObject> getControlableObjects() {	return control;		}
	@Override public int getWidth() {	return width;  }
	@Override public int getHeight() { return height; }
	@Override 
	public String getStatus() {
		return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
	}
}