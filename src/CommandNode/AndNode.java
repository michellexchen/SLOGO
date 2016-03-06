package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class AndNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) != 0 && getChildren().get(0).evaluate(state) != 0 ? 1 : 0;
	}
	
}