package eg.edu.alexu.csd.oop.game.state;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;
import eg.edu.alexu.csd.oop.game.state.StateCup;

import java.util.List;

public class stableState implements StateCup {
    private ImageObject salah;
    private ImageObject rightCup;
    private ImageObject leftCup;
    private   List<GameObject> ConstantElement ;
    private   List<GameObject> control ;
    private   List<GameObject> rightCupContents;
    private   List<GameObject> leftCupContents ;
    private int screenWidth;
    private StateCup edge;
    public stableState(ImageObject salah, ImageObject rightCup, ImageObject leftCup, List<GameObject> constantElement, List<GameObject> control, List<GameObject> rightCupContents, List<GameObject> leftCupContents, int screenWidth) {
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

    public stableState setEdge(StateCup edge) {
        this.edge = edge;
        return this;
    }

    public void MakeMovalble(){
        MakeMovable(rightCup);
        MakeMovable(leftCup);
        leftCup.setX(salah.getX()+5);
        rightCup.setX(salah.getX()+220);
        for(GameObject imageObject:rightCupContents){
            MakeMovable((ImageObject)imageObject);
            imageObject.setX(rightCup.getX()+10);
        }
        for(GameObject imageObject:leftCupContents){
            MakeMovable((ImageObject)imageObject);
            imageObject.setX(leftCup.getX()+10);
        }

    }
    private void MakeMovable(ImageObject imageObject){
        this.control.add(imageObject);
        this.ConstantElement.remove(imageObject);
    }

    @Override
    public void Remove(List<GameObject> CUP, int numOfBalls) {

        int n = CUP.size();
        for(int i=1;i<=numOfBalls;i++){
            ImageObject imageObject = (ImageObject) CUP.get(n-i);
            control.remove(imageObject);
            CUP.remove(n-i);

        }

    }

    @Override
    public StateCup check(int xSalah) {
        if (xSalah == 0 || xSalah ==screenWidth-300 ) {
            ((EdgeState) edge).makeConstant();
            return edge;
        }
        return this;

    }
}
