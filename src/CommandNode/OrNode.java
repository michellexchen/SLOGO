package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class OrNode extends BinaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) != 0 || getChildren().get(0).evaluate(state) != 0 ? 1 : 0;
	}
}