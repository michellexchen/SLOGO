package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetXYNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double distance = calculateDistance(state.getXCoor(), state.getYCoor(), state);
		state.setXCoor(evaluateChild(0, state));
		state.setYCoor(evaluateChild(1, state));
		return distance;
	}

	private double calculateDistance(double x, double y, SLogoCharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(evaluateChild(0, state) - x, 2)
				- Math.pow(evaluateChild(1, state) - y, 2));
	}
	
}