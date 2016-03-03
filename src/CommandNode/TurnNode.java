package commandNode;

import exception.SLogoException;
import model.CharacterState;

public abstract class TurnNode extends TurtleCommandNode {

	public double evaluate(CharacterState currentState) throws SLogoException {
		double newDirection = calculateDir(currentState);
		double diff = Math.abs(newDirection - currentState.getDirection());
		currentState.setDirection(newDirection);
		return diff;
	}

	public abstract double calculateDir(CharacterState state) throws SLogoException;

}