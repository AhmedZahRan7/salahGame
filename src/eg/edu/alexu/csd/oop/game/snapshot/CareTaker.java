package eg.edu.alexu.csd.oop.game.snapshot;

import eg.edu.alexu.csd.oop.game.snapshot.Memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
    public List getList(){
        return mementoList;
    }
}