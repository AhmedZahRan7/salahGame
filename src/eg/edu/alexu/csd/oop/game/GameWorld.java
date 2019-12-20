package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.Levels.Level;
import eg.edu.alexu.csd.oop.game.MainGui.MainFrame;
import eg.edu.alexu.csd.oop.game.snapshot.CareTaker;
import eg.edu.alexu.csd.oop.game.snapshot.Originator;
import eg.edu.alexu.csd.oop.game.sound.SoundFactory;
import eg.edu.alexu.csd.oop.game.state.EdgeState;
import eg.edu.alexu.csd.oop.game.state.StateCup;
import eg.edu.alexu.csd.oop.game.state.stableState;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public  class GameWorld implements World {
    private static final int BALL_HEIGHT=50;

    private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    // for ramos and balls
    private final List<GameObject> movingElements = new LinkedList<GameObject>();
    // for background
    private final List<GameObject> ConstantElement = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> rightCupContents = new LinkedList<GameObject>();
    private final List<GameObject> leftCupContents = new LinkedList<GameObject>();
    private ImageObject salah;
    private ImageObject rightCup;
    private ImageObject leftCup;
    private StateCup edgeState;
    private StateCup stableState;
    private StateCup currentState;
    private Level lev;
    private static final int TYPE_OF_ENEMY=100;
    private static final int TYPE_OF_BALLS=200;

    private Originator originator;
    private CareTaker careTaker;
    public GameWorld(int screenWidth, int screenHeight , Level lev) {
        this.width = screenWidth;
        this.height = screenHeight;
        this.lev=lev;

        originator = new Originator();
        originator.setLevel(lev);
        careTaker = new CareTaker();
        careTaker.add(originator.saveStateToMemento());

        ImageStore imageStore =ImageStore.getInstance();
        salah=(new ImageObject(screenWidth/3, (int)(screenHeight*0.5), imageStore.getSalah(),true,0));
        control.add(salah);
        leftCup=new ImageObject(control.get(0).getX()+5,control.get(0).getY()+83,imageStore.getCup(),true,0);
        rightCup=new ImageObject(control.get(0).getX()+220,control.get(0).getY()+83,imageStore.getCup(),true,0);



        control.add(leftCup);
        control.add(rightCup);

        edgeState =new EdgeState(salah,rightCup,leftCup,ConstantElement,control,rightCupContents,leftCupContents,screenWidth);
        stableState =new stableState(salah,rightCup,leftCup,ConstantElement,control,rightCupContents,leftCupContents,screenWidth);
        currentState=stableState;

        ((EdgeState)edgeState).setStable(stableState);
        ((stableState)stableState).setEdge(edgeState);
        lev.setConstantElement(getConstantObjects());
        lev.setControl(getControlableObjects());
        lev.setHeight(screenHeight);
        lev.setWidth(screenWidth);
        lev.setMovable(getMovableObjects());

        ballFactory.ReadBalls(screenWidth, screenHeight);

        for(int i=0; i<lev.getNumOfEnemy(); i++)
            movingElements.add(new ImageObject((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight), imageStore.getRamous(),false,TYPE_OF_ENEMY));
        for(int i=0; i<lev.getNumOfballs(); i++){
            movingElements.add(ballFactory.getRandomBall(screenHeight,screenWidth));
           // movingElements.add(new ImageObject((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight), ballFactory.getRandomBall(screenWidth,screenHeight),false,TYPE_OF_BALLS));

        }

        ConstantElement.add(new ImageObject(0,0, imageStore.getBack(),3));

    }


    private boolean intersect(GameObject o1, GameObject o2){
        return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
    }


    @Override
    public int getSpeed() {
        return 10;
    }
    @Override
    public int getControlSpeed() {
        return 10;
    }
    @Override
    public List<GameObject> getConstantObjects() {
        return ConstantElement;
    }
    @Override
    public List<GameObject> getMovableObjects() {
        return movingElements;
    }
    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }


    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        if(System.currentTimeMillis() % 1000 == 0) {
            originator.setLevel(lev.clone());
            careTaker.add(originator.saveStateToMemento());
        }
        GameObject salah=this.salah;
        GameObject leftCup = this.leftCup;
        GameObject rightCup = this.rightCup;

        // check state with each refresh to determine if the cup is act as constant ao act as control
        currentState=currentState.check(salah.getX());
        //currentState.check(currentState,edgeState,stableState,salah.getX());
        // next two line to get the element for each cup which will check it if it is intersected with ball or not
        // for example in the begin of game this element will be the cup itself but after that it will be the last ball for each cup
        if(leftCupContents.size()>0)leftCup=leftCupContents.get(leftCupContents.size()-1);
        if(rightCupContents.size()>0)rightCup=rightCupContents.get(rightCupContents.size()-1);

        if(leftCup.getY()<0&&rightCup.getY()<0){
            timeout=true;
        }
        // moving starts
        for(GameObject c : movingElements)
        {
            // check if the image for ramos
            if (((ImageObject) c).getType()==TYPE_OF_ENEMY)
            {
                c.setY((c.getY() +lev.ShiftY()));
                if (c.getY() >= getHeight())
                {
                    RepeatProcess(c);
                }
                c.setX(c.getX() + (Math.random() > 0.5 ? 1 : -1));
                if (!timeout && intersect(c, salah))
                {
                    SoundFactory.playFoulSound();
                    score = Math.max(0, score - 1);    // lose score
                    RepeatProcess(c);
                }
                // this means it is a ball image
            }else {
                c.setY(c.getY()+lev.ShiftY());
                if(!timeout && intersect(c,rightCup))
                {
                    addElement(c,rightCupContents,(ImageObject) rightCup);
                    if(lev.checkLast(rightCupContents)){

                        SoundFactory.PlayGoal();
                        score++;
                        currentState.Remove(rightCupContents,lev.getNumOfRequiredBalls());
                    }
                    RepeatProcess(c);
                }
                else if(!timeout && intersect(c,leftCup)){
                    addElement(c,leftCupContents,(ImageObject) leftCup);
                    if(lev.checkLast(leftCupContents)){
                      //  SoundFactory.playRandomGoalSound();
                        SoundFactory.PlayGoal();
                        score++;
                        currentState.Remove(leftCupContents,lev.getNumOfRequiredBalls());
                    }
                    RepeatProcess(c);
                }
                else if(c.getY()>=getHeight()){
                    RepeatProcess(c);
                }
            }
        }

        return !timeout;
    }


    private void RepeatProcess(GameObject c){
        c.setY(-1 * (int)(Math.random() * getHeight()));
        c.setX((int)(Math.random() * getWidth()));
    }

    private void addElement(GameObject c, List<GameObject> CUP, ImageObject cup) {
        c.setY(cup.getY()-BALL_HEIGHT);
        c.setX(calcNewX(cup));
        ImageObject temp=((ImageObject) c).clone();
        CUP.add(temp);

        // if we are in edgeState then the ball will be act as constant
        if(currentState==edgeState)
            ConstantElement.add(temp);
        else
            control.add(temp);
    }

    public CareTaker getCareTaker() {
        return careTaker;
    }

    private int calcNewX(ImageObject imageObject){
        int shift=0;
        if(imageObject==rightCup||imageObject==leftCup)shift=10;
        return imageObject.getX()+shift;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
    }
}
