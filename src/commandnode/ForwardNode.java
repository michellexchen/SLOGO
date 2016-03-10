package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ForwardNode extends UnaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double[] newCoor = calculateLoc(state.getDirection(), state);
		state.setXCoor(boundCoor(state.getXCoor() + newCoor[0]));
		state.setYCoor(boundCoor(state.getYCoor() + newCoor[1]));
		return evaluateChild(0, state);
	}

	private double[] calculateLoc(double direction, SLogoCharacterState state) throws SLogoException {
		double[] result = new double[2];
		result[0] = Math.sin(Math.toRadians(direction)) * evaluateChild(0, state);
		result[1] = -Math.cos(Math.toRadians(direction)) * evaluateChild(0, state);
		return result;
	}

}