package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetXYNode extends BinaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double distance = calculateDistance(currentState.getXCoor(), currentState.getYCoor(), currentState);
		currentState.setXCoor(boundCoor(transformXCoor(getChildren().get(0).evaluate(currentState)))); //Replace 500 with X midpoint
		currentState.setYCoor(boundCoor(transformYCoor(getChildren().get(1).evaluate(currentState)))); //Replace 500 with Y midpoint
		return distance;
	}

	private double calculateDistance(double x, double y, SLogoCharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(boundCoor(transformXCoor(getChildren().get(0).evaluate(state))) - x, 2) //Replace 500 with X midpoint
				- Math.pow(boundCoor(transformYCoor(getChildren().get(1).evaluate(state))) - y, 2)); //Replace 500 with Y midpoint
	}
	
}