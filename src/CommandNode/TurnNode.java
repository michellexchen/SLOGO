package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public abstract class TurnNode extends UnaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double newDirection = calculateDir(currentState);
		double diff = Math.abs(newDirection - currentState.getDirection());
		currentState.setDirection(newDirection);
		return diff;
	}

	public abstract double calculateDir(SLogoCharacterState state) throws SLogoException;

}