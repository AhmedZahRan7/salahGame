package eg.edu.alexu.csd.oop.game.snapshot;

import eg.edu.alexu.csd.oop.game.Levels.Level;

public class Originator {
    private Level level;
    public void setLevel(Level level){
        this.level=level;
    }
    public Level getLevel(){
        return level;
    }
    public Memento saveStateToMemento(){
        return new Memento(level);
    }
    public void getStateFromMemento(Memento memento){
        level = memento.getLevel();
    }
}
