package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class NotEqualNode extends EqualityNode {

	public double evaluate(CharacterState state) throws SLogoException {
		return isEqual(state) ? 0 : 1;
	}
	
}