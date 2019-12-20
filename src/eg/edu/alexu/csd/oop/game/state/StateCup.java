package eg.edu.alexu.csd.oop.game.state;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.List;

public interface StateCup  {

    void Remove(List<GameObject> CUP, int numOfBalls);
    StateCup check( int xSalah) ;
}
