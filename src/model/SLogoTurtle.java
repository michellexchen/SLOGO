package model;

import exception.SLogoException;

/**
 * SLogo's Turtle with a TurtleState and name
 *
 */

public class SLogoTurtle implements SLogoCharacter{

	private SLogoTurtleState myState;
	private int myID;

	public SLogoTurtle(int ID, SLogoPen myPen, double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden, String myImage) throws SLogoException {
		myState = new SLogoTurtleState(myPen, xCoor, yCoor, direction, isHidden, penDown, myImage);
		System.out.println("tutle " + myID + " current state is: location xCoor " + xCoor);
	}

	public void setState(SLogoCharacterState myState) {
		this.myState = (SLogoTurtleState) myState;
		System.out.println("tutle " + myState.getXCoor() + " current state is: location xCoor " + myState.getXCoor());
	}

	public SLogoCharacterState getState() {
		return myState;
	}

	public int getID() {
		return myID;
	}

}