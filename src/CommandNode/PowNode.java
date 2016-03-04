package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class PowNode extends BinaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException {
		double base = getChildren().get(0).evaluate(state);
		double exponent = getChildren().get(1).evaluate(state);
		return Math.pow(base, exponent);
	}
}