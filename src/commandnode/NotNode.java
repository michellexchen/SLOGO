package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class NotNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return evaluateChild(0, state) == 0 ? 1 : 0;
	}
}