package eg.edu.alexu.csd.oop.game.ballsVersions;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;

public class Ball5 implements Ball {

    /** path of this type of ball */
    private final String path = "/Balls/ball5.png";
    /** screen width */
    private int screenWidth = 1350;
    /** screen height */
    private int screenHeight = 700;

    public Ball5(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public GameObject generateThis(){
        return new ImageObject((int)(this.screenWidth*0.9*Math.random()), (int)(this.screenHeight*0.9*Math.random()), this.path,false,5);
    }
}
