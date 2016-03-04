package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class OrNode extends BinaryNode {

	public double evaluate(CharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) != 0 || getChildren().get(0).evaluate(state) != 0 ? 1 : 0;
	}
}