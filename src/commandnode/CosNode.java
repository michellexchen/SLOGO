package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class CosNode extends UnaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.cos(evaluateChild(0, state));
	}

}