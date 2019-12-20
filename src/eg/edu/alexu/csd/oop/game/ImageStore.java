package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.ballsVersions.Ball;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

public  class ImageStore {
    private ImageStore()  { }

    private static ImageStore store;
    BufferedImage salah;
    BufferedImage cup;
    BufferedImage ramous;
    BufferedImage back;
    Vector<Object[]>  Balls;
    boolean loaded=false;

    {
        try {
             salah = ImageIO.read(getClass().getResourceAsStream("/salah.png"));
             cup=ImageIO.read(getClass().getResourceAsStream("/cup.png"));
             ramous=ImageIO.read(getClass().getResourceAsStream("/ramos.png"));
             back=ImageIO.read(getClass().getResourceAsStream("/stadiumBack.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageStore getInstance(){
        if(store==null)
                store=new ImageStore();
        return store;
    }
    public void  LoadBalls(Vector<GameObject> imageObjectVector){
        if(loaded)return;
        loaded=true;
        Balls=new Vector<>();
        for(GameObject imageObject :imageObjectVector){
            Object[] objects=new Object[2];
            objects[0]=imageObject.getSpriteImages()[0];
            objects[1]=((ImageObject)imageObject).getType();
            Balls.add(objects);
        }
    }

    public Object[] getBall(int num){
        return Balls.get(num);
    }

    public BufferedImage getSalah() {
        return salah;
    }

    public BufferedImage getCup() {
        return cup;
    }

    public BufferedImage getRamous() {
        return ramous;
    }

    public BufferedImage getBack() {
        return back;
    }
}
