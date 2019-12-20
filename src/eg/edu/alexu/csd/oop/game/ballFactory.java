package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.Loader.BallsLoader;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Vector;

public class ballFactory {
    /** array contains balls data Objects */
    private static Vector<GameObject> balls = null;
    /** array contains enemies data Objects */
    private static Vector<GameObject> enemies = null;

    public static void ReadBalls(int screenWidth, int screenHeight) {
        BallsLoader ballsLoader = new BallsLoader(screenWidth, screenHeight, "ballsVersions");
        BallsLoader enemiesLoader = new BallsLoader(screenWidth, screenHeight, "enemiesVersions");
        ballsLoader.invokeClassMethod();
        balls = ballsLoader.getData();
        enemies = enemiesLoader.getData();
        ImageStore.getInstance().LoadBalls(balls);
    }



    public static GameObject getRandomBall(int screenHeight, int screenWidth) {
        int temp = (int) (Math.random() * (balls.size()+1)) % balls.size();
        Object[] objects=ImageStore.getInstance().getBall(temp);

       return new ImageObject((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight), (BufferedImage)objects[0],false,(int)objects[1]);

    }
}
