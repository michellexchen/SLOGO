package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetXYNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double distance = calculateDistance(state.getXCoor(), state.getYCoor(), state);
		state.setXCoor(getChildren().get(0).evaluate(state));
		state.setYCoor(getChildren().get(1).evaluate(state));
		return distance;
	}

	private double calculateDistance(double x, double y, SLogoCharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(getChildren().get(0).evaluate(state) - x, 2)
				- Math.pow(getChildren().get(1).evaluate(state) - y, 2));
	}
	
}