package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class BackwardNode extends StraightCommand {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + -1 * newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + -1 * newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}

}