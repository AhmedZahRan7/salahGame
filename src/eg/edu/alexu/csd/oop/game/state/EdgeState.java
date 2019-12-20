package eg.edu.alexu.csd.oop.game.state;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;

import java.util.List;

public class EdgeState implements StateCup {
    protected ImageObject salah;
    protected ImageObject rightCup;
    protected ImageObject leftCup;
    protected  List<GameObject> ConstantElement ;
    protected  List<GameObject> control ;
    protected  List<GameObject> rightCupContents;
    protected  List<GameObject> leftCupContents ;
    private StateCup stable;
    private int screenWidth;

    public EdgeState(ImageObject salah, ImageObject rightCup, ImageObject leftCup, List<GameObject> constantElement, List<GameObject> control, List<GameObject> rightCupContents, List<GameObject> leftCupContents,int screenWidth) {
        this.salah = salah;
        this.rightCup = rightCup;
        this.leftCup = leftCup;
        ConstantElement = constantElement;
        this.control = control;
        this.rightCupContents = rightCupContents;
        this.leftCupContents = leftCupContents;
        this.screenWidth=screenWidth;
        if(screenWidth>1540)this.screenWidth=1540;
    }


    public EdgeState setStable(StateCup stable) {
        this.stable = stable;
        return this;
    }

    public void makeConstant(){
        MakeConstant(rightCup);
        MakeConstant(leftCup);
        for(GameObject imageObject:rightCupContents){
            MakeConstant((ImageObject)imageObject);
        }
        for(GameObject imageObject:leftCupContents){
            MakeConstant((ImageObject)imageObject);
        }

        return;

    }
    private void MakeConstant(ImageObject imageObject){
        this.control.remove(imageObject);
        this.ConstantElement.add(imageObject);
    }

    @Override
    public void Remove(List<GameObject> CUP, int numOfBalls) {
        int n = CUP.size();
        for(int i=1;i<=numOfBalls;i++){
            ImageObject imageObject = (ImageObject) CUP.get(n-i);
            ConstantElement.remove(imageObject);
            CUP.remove(n-i);
        }
    }

    @Override
    public StateCup check( int xSalah) {

        if(xSalah == 0 || xSalah == screenWidth-300){
            return this;
        }
        ((stableState) stable).MakeMovalble();
        return  stable;
    }

}
