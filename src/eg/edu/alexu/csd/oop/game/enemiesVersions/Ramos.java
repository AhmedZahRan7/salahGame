package eg.edu.alexu.csd.oop.game.enemiesVersions;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;

public class Ramos {

    public static GameObject getRandomBall(int screenWidth, int screenHeight) {
        return generateThis(screenWidth,screenHeight,"Enemies/ramos.png");
    }

    private static GameObject generateThis(int screenWidth, int screenHeight, String path){
        return new ImageObject((int)(screenWidth*0.9*Math.random()), (int)(screenHeight*0.9*Math.random()), path,false,100);
    }
}
