package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of SetShape, Setsh commands, Display commands
 */
public class SetShapeNode extends UnaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double index = evaluateChild(0, state);
		state.setShapeIndex((int) index);
		return index;
	}
}