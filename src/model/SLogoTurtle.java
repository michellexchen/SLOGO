package model;

import exception.SLogoException;

/**
 * SLogo's Turtle with a TurtleState
 *
 */
public class SLogoTurtle implements SLogoCharacter{

    private SLogoTurtleState myState;
    private int ID;

    public SLogoTurtle(int ID, SLogoPen myPen, double xCoor, double yCoor, 
                       boolean penDown, double direction, boolean isHidden, 
                       int shapeIndex) throws SLogoException {
        myState = new SLogoTurtleState(myPen, xCoor, yCoor, direction, 
                                       isHidden, penDown, shapeIndex);
        System.out.println("tutle " + myState.getID() + " current state is: location xCoor " + xCoor);
        this.ID = ID;
    }

    /**
     * @param myState: next state of turtle
     * Updates the state of the turtle
     */
    public void setState(SLogoCharacterState myState) {
        this.myState = (SLogoTurtleState) myState;
        System.out.println("tutle " + myState.getXCoor() + " current state is: location xCoor " + myState.getXCoor());
    }

    public SLogoCharacterState getState() {
        return myState;
    }

    public int getCurrTurtlesID(){
        return this.ID;
    }

}