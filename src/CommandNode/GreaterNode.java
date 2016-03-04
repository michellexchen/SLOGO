package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class GreaterNode extends BinaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) < getChildren().get(1).evaluate(state) ? 0 : 1;
	}
}