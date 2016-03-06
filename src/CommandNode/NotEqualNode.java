package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class NotEqualNode extends EqualityNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return isEqual(state) ? 0 : 1;
	}
	
}