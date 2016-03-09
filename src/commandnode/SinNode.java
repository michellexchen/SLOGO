package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SinNode extends UnaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.sin(Math.toRadians(evaluateChild(0, state)));
	}

}