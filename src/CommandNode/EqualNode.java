package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class EqualNode extends EqualityNode {

	public double evaluate(CharacterState state) throws SLogoException {
		return isEqual(state) ? 1 : 0;
	}
	
}