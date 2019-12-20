package eg.edu.alexu.csd.oop.game.sample.object;

public class SpaceFighterObject extends ImageObject{

	public static interface FireListener{
		void fire();
	}
	
	private FireListener fireListener;
	
	public SpaceFighterObject(int posX, int posY, String path, FireListener fireListener) {
		super(posX, posY, path);
		this.fireListener = fireListener;
	}
	
	@Override
	public void setY(int mY) {
		if(fireListener!=null)
			fireListener.fire();
	}

}
