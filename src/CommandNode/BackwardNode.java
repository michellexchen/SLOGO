package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class BackwardNode extends UnaryNode {

	public double evaluate(CharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() - newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}

	private double[] calculateLoc(double direction, CharacterState state) throws SLogoException {
		double[] result = new double[2];
		result[0] = -Math.sin(direction) * getChildren().get(0).evaluate(state);
		result[1] = -Math.cos(direction) * getChildren().get(0).evaluate(state);
		return result;
	}

}