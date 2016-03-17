package model;

import exception.SLogoException;

/**
 * SLogo's Turtle with a TurtleState
 *
 */
public class SLogoTurtle implements SLogoCharacter{

    private SLogoTurtleState myState;
    private int ID;

    /**
     * Default constructor that sets fields that define a turtle
     * 
     * @param ID
     * @param myPen
     * @param xCoor
     * @param yCoor
     * @param penDown
     * @param direction
     * @param isHidden
     * @param shapeIndex
     * @throws SLogoException
     */
    public SLogoTurtle (int ID, SLogoPen myPen, double xCoor, double yCoor, double direction, boolean isHidden, 
                        int shapeIndex) throws SLogoException {
        myState = new SLogoTurtleState(myPen, xCoor, yCoor, direction, 
                                       isHidden, shapeIndex);
        this.ID = ID;
    }

    /**
     * @param myState: next state of turtle
     * Updates the state of the turtle
     */
    public void setState(SLogoCharacterState myState) {
        this.myState = (SLogoTurtleState) myState;
    }

    public SLogoCharacterState getState() {
        return myState;
    }

    public int getCurrTurtlesID(){
        return this.ID;
    }

}