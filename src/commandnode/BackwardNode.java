package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class BackwardNode extends UnaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}

	private double[] calculateLoc(double direction, SLogoCharacterState state) throws SLogoException {
		double[] result = new double[2];
		result[0] = -Math.sin(Math.toRadians(direction)) * evaluateChild(0, state);
		result[1] = -Math.cos(Math.toRadians(direction)) * evaluateChild(0, state);
		return result;
	}

}