package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Difference, Subtract, or - commands
 */
public class DifferenceNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return evaluateChild(0, state) - evaluateChild(1, state);
	}
}