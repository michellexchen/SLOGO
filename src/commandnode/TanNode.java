package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TanNode extends UnaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.tan(evaluateChild(0, state));
	}

}