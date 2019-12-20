package eg.edu.alexu.csd.oop.game.Loader;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ballsVersions.Ball;
import eg.edu.alexu.csd.oop.game.ballsVersions.Ball1;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.SplittableRandom;
import java.util.Vector;
import java.util.Arrays;

public class BallsLoader extends ClassLoader {

    /** balls array names */
    private String[] listOfNames = null;
    /** vector of loaded classes */
    private Vector<GameObject> BallsLoadedClasses = null;
    /** path of data */
    private String path = null;
    /** screen width */
    private int screenWidth;
    /** screen Height */
    private int screenHeight;
    /** file system separator */
    private final String separator = File.separator;
    /** relative path */
    private  String earlyPath ;

    public BallsLoader(int screenWidth, int screenHeight, String path) {
        earlyPath= ".src.eg.edu.alexu.csd.oop.game.".replaceAll("\\.","\\"+ separator);;
        this.path = System.getProperty("user.dir") + earlyPath + path;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.BallsLoadedClasses = new Vector<GameObject>();
        this.readFiles();
    }

    private void readFiles() {
        Path pathx = FileSystems.getDefault().getPath(this.path);
        if(Files.exists(pathx))
            System.out.println(path);
        File folder = new File(this.path);
        LinkedList<String> dataNames = new LinkedList<>(Arrays.asList(folder.list()));
        String[] temp = new String[2];
        for (int i = 0; i < dataNames.size(); ++i) {
            temp = dataNames.get(i).split("\\.");
            dataNames.set(i, temp[0]);
        }
        dataNames.remove("Ball");
        listOfNames = new String[dataNames.size()];
        for (int i = 0; i < dataNames.size(); ++i)
               listOfNames[i] = dataNames.get(i);
    }

    public void invokeClassMethod() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        for(String classBinName : listOfNames) {
            try {
                Class<?> thisBall = classLoader.loadClass("eg.edu.alexu.csd.oop.game.ballsVersions." + classBinName);
                Constructor<?> constructor = thisBall.getConstructor(int.class, int.class);
                Object myClassObject = constructor.newInstance(this.screenWidth, this.screenHeight);
                if(Ball.class.isInstance(myClassObject)) {
                    System.out.println(thisBall.getName());
                    Method method = thisBall.getMethod("generateThis");
                    this.BallsLoadedClasses.add((GameObject) method.invoke(myClassObject));
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Vector<GameObject> getData() {
        return this.BallsLoadedClasses;
    }

}
