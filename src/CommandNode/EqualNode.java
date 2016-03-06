package CommandNode;

import Model.*;
import Exception.*;

public class EqualNode extends EqualityNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return isEqual(state) ? 1 : 0;
	}
	
}