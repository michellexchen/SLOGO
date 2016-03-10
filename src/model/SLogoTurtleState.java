package model;

import exception.SLogoException;

/**
 * SLogo's TurtleState, a class extending abstract CharacterState to control
 * Turtle-only functions (currently pen)
 *
 */

public class SLogoTurtleState extends SLogoCharacterState {

	public SLogoTurtleState(SLogoPen myPen, double xCoor, double yCoor, double direction, boolean isHidden, boolean penDown) throws SLogoException {
		super(myPen, xCoor, yCoor, direction, isHidden);
	}

}