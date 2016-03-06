package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class PowNode extends BinaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double base = getChildren().get(0).evaluate(state);
		double exponent = getChildren().get(1).evaluate(state);
		return Math.pow(base, exponent);
	}
}