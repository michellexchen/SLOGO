package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class HomeNode extends NullaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double distance = calculateDistance(state.getXCoor(), state.getYCoor(), state);
		state.setXCoor(0);
		state.setYCoor(0);
		return distance;
	}

	private double calculateDistance(double x, double y, SLogoCharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}