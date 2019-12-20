//package eg.edu.alexu.csd.oop.game.replay;
//import eg.edu.alexu.csd.oop.game.GameObject;
//import eg.edu.alexu.csd.oop.game.Levels.Level;
//import eg.edu.alexu.csd.oop.game.snapshot.CareTaker;
//import eg.edu.alexu.csd.oop.game.snapshot.Memento;
//
//
//public class Replayer  {
//    public Replayer(CareTaker careTaker){
//        for (Object level : careTaker.getList()) levelDrawer((Memento) level);
//    }
//
//    private void levelDrawer(Memento level){
//        System.out.println("level ----- \n\n");
//        printLevelDetails(level.getLevel());
//    }
//
//    private void printLevelDetails(Level lev){
//        System.out.println("const");
//        for(GameObject c : lev.ConstantElement){
//            System.out.println(c.getClass().getName());
//            System.out.println("x: "+c.getX() +"y: "+c.getY());
//        }
//        System.out.println("control");
//        for(GameObject c : lev.control){
//            System.out.println(c.getClass().getName());
//            System.out.println("x: "+c.getX() +"y: "+c.getY());
//        }
//        System.out.println("movable");
//        for(GameObject c : lev.movable){
//            System.out.println(c.getClass().getName());
//            System.out.println("x: "+c.getX() +"y: "+c.getY());
//        }
//    }
//
//}
