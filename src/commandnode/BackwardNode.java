package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class BackwardNode extends UnaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(boundCoor(currentState.getXCoor() - newCoor[0]));
		currentState.setYCoor(boundCoor(currentState.getYCoor() - newCoor[1]));
		return getChildren().get(0).evaluate(currentState);
	}

	private double[] calculateLoc(double direction, SLogoCharacterState state) throws SLogoException {
		double[] result = new double[2];
		result[0] = Math.sin(direction) * getChildren().get(0).evaluate(state);
		result[1] = -Math.cos(direction) * getChildren().get(0).evaluate(state);
		return result;
	}

}