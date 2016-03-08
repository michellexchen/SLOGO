package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class PowNode extends BinaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double base = evaluateChild(0, state);
		double exponent = evaluateChild(1, state);
		return Math.pow(base, exponent);
	}
}