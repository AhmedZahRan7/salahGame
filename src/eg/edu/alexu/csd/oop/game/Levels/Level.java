package eg.edu.alexu.csd.oop.game.Levels;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;

import java.util.List;

public abstract class Level implements Cloneable{
    public List<GameObject> ConstantElement;
    public List<GameObject> control ;

    public void setMovable(List<GameObject> movable) {
        this.movable = movable;
    }

    public List<GameObject>movable;
    public int height;
    public int width;

    public  boolean checkLast(List<GameObject> Cup){
        int x =getNumOfRequiredBalls();
        int n =Cup.size();
        int gameObject = ((ImageObject)Cup.get(n - 1)).getType();
        if(n<x)return false;
        for(int i=2;i<=x;i++){
            if(gameObject!=(((ImageObject) Cup.get(n - i)).getType()))return false;
        }
        return true;

    }


    public abstract int getNumOfRequiredBalls();
    public abstract int ShiftY();
    public void setConstantElement(List<GameObject> constantElement) {
        ConstantElement = constantElement;
    }

    public void setControl(List<GameObject> control) {
        this.control = control;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public abstract int getNumOfEnemy();

    public abstract int getNumOfballs();

    public abstract Level clone();

}