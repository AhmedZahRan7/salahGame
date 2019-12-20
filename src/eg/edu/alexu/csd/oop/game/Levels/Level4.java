package eg.edu.alexu.csd.oop.game.Levels;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;

import java.util.LinkedList;

public class Level4 extends Level {




    @Override
    public int getNumOfRequiredBalls() {
        return 3;
    }


    @Override
    public int ShiftY() {
        double num = Math.random();
        if(num<.1)return 1;
        if(num<.4)return 3;
        if(num<.9)return 5;
        return 10;
    }


    @Override
    public int getNumOfEnemy() {
        return 6;
    }

    @Override
    public int getNumOfballs() {
        return 12;
    }

    @Override
    public Level clone() {
        Level level = new Level4();
        LinkedList<GameObject> newConstants = new LinkedList<>();
        for (GameObject o : this.ConstantElement) newConstants.add(((ImageObject)o).clone());
        level.setConstantElement(newConstants);

        LinkedList<GameObject> newControl = new LinkedList<>();
        for (GameObject o : this.control) newControl.add(((ImageObject)o).clone());
        level.setConstantElement(newControl);

        LinkedList<GameObject> newMovable = new LinkedList<>();
        for (GameObject o : this.movable) newMovable.add(((ImageObject)o).clone());
        level.setConstantElement(newMovable);

        level.setHeight(this.height);
        level.setWidth(this.width);
        return level;
    }

}
