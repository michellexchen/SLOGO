package model;

/**
 * SLogo's Turtle with a TurtleState and name
 *
 */

public class SLogoTurtle implements SLogoCharacter {

	SLogoTurtleState myState;
	String myName;

	public SLogoTurtle(String myName, double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden,
			double angle) {
		myState = new SLogoTurtleState(xCoor, yCoor, direction, isHidden, penDown, angle);
		System.out.println("tutle " + myName + " current state is: location xCoor " + xCoor);
		this.myName = myName;
	}

	public void setState(SLogoCharacterState myState) {
		this.myState = (SLogoTurtleState) myState;
		System.out.println("tutle " + myState.getXCoor() + " current state is: location xCoor " + myState.getXCoor());
	}

	public SLogoCharacterState getState() {
		return myState;
	}

	public String getName() {
		return myName;
	}

}