package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public abstract class TurnNode extends UnaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double newDirection = calculateDir(currentState);
		double diff = Math.abs(newDirection - currentState.getDirection());
		currentState.setDirection(newDirection);
		return diff;
	}

	public abstract double calculateDir(SLogoCharacterState state) throws SLogoException;

}