package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetXYNode extends BinaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double distance = calculateDistance(currentState.getXCoor(), currentState.getYCoor(), currentState);
		currentState.setXCoor(Math.abs(getChildren().get(0).evaluate(currentState) + 500)); //Replace 500 with X midpoint
		currentState.setYCoor(Math.abs(getChildren().get(1).evaluate(currentState) - 500)); //Replace 500 with Y midpoint
		return distance;
	}

	private double calculateDistance(double x, double y, SLogoCharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(getChildren().get(0).evaluate(state) + 500 - x, 2) //Replace 500 with X midpoint
				- Math.pow(Math.abs(getChildren().get(1).evaluate(state) - 500) - y, 2)); //Replace 500 with Y midpoint
	}
	
}