package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * @author Aaron
 * Node representation of Backward, Back, Bk commands that extends StraightCommand
 * Flips x and y coordinates of calculated location to move backwards not forwards
 */
public class BackwardNode extends StraightCommand {

	/**
	 * @return pixel distance moved backward based on current direction
	 */
	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + -1 * newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + -1 * newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}

}