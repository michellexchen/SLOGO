package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public abstract class TurnNode extends UnaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double newDirection = convertDir(calculateDir(currentState));
		double diff = Math.abs(newDirection - currentState.getDirection());
		currentState.setDirection(newDirection);
		return Math.toDegrees(diff);
	}
	
	public double convertDir(double direction) {
		double result;
		if (direction > 360) {
			result = direction % 360;
		}
		else if(direction < 0) {
			result = 360 - (Math.abs(direction) % 360);
		}
		else {
			result = direction;
		}
		return Math.toRadians(result);
	}

	public abstract double calculateDir(SLogoCharacterState state) throws SLogoException;

}