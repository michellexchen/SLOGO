package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class LessNode extends BinaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return evaluateChild(0, state) < evaluateChild(1, state) ? 1 : 0;
	}
}