package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public abstract class TurnNode extends TurtleCommandNode {

	public double evaluate(CharacterState currentState) throws SLogoException {
		double newDirection = calculateDir(currentState.getXCoor(), currentState.getYCoor(), currentState);
		double diff = currentState.getDirection() - newDirection;
		currentState.setDirection(newDirection);
		return diff;
	}

	public abstract double calculateDir(double x, double y, CharacterState state) throws SLogoException;

}