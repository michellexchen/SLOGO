package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class HomeNode extends NullaryNode{

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double distance = calculateDistance(currentState);
		currentState.setXCoor(transformXCoor(0)); //Replace 500 with X midpoint
		currentState.setYCoor(transformYCoor(0)); //Replace 500 with Y midpoint
		return distance;
	} 

	private double calculateDistance(SLogoCharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(boundCoor(transformXCoor(0)) - state.getXCoor(), 2) //Replace 500 with X midpoint
				- Math.pow(boundCoor(transformYCoor(0)) - state.getYCoor(), 2)); //Replace 500 with Y midpoint
	}
}
