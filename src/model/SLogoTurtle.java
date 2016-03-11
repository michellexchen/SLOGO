package model;

import exception.SLogoException;

/**
 * SLogo's Turtle with a TurtleState
 *
 */

public class SLogoTurtle implements SLogoCharacter{

	private SLogoTurtleState myState;

	public SLogoTurtle(int ID, SLogoPen myPen, double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden, int shapeIndex) throws SLogoException {
		myState = new SLogoTurtleState(myPen, xCoor, yCoor, direction, isHidden, penDown, shapeIndex);
		System.out.println("tutle " + myState.getID() + " current state is: location xCoor " + xCoor);
	}

	public void setState(SLogoCharacterState myState) {
		this.myState = (SLogoTurtleState) myState;
		System.out.println("tutle " + myState.getXCoor() + " current state is: location xCoor " + myState.getXCoor());
	}

	public SLogoCharacterState getState() {
		return myState;
	}

}