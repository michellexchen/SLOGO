package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ArctanNode extends UnaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.atan(evaluateChild(0, state));
	}

}