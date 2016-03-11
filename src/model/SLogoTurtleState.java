package model;

import exception.SLogoException;

/**
 * SLogo's TurtleState, a class extending abstract CharacterState
 *
 */

public class SLogoTurtleState extends SLogoCharacterState {

	public SLogoTurtleState(SLogoPen myPen, double xCoor, double yCoor, double direction, boolean isHidden, boolean penDown, int shapeIndex) throws SLogoException {
		super(myPen, xCoor, yCoor, direction, isHidden, shapeIndex);
	}

}