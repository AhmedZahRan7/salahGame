package eg.edu.alexu.csd.oop.game.snapshot;

import eg.edu.alexu.csd.oop.game.ImageObject;
import eg.edu.alexu.csd.oop.game.Levels.Level1;
import eg.edu.alexu.csd.oop.game.Levels.Level;

import java.util.List;

public class Memento {
   private Level level;

    public Level getLevel() {
        return level;
    }

    public Memento(Level level){
        this.level = level;
    }

}
