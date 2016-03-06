package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class EqualNode extends EqualityNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return isEqual(state) ? 1 : 0;
	}
	
}