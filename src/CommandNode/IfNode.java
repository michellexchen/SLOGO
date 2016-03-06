package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class IfNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) != 0 ? getChildren().get(1).evaluate(state) : 0;
	}

}