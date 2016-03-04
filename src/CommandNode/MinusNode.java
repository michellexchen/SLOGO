package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class MinusNode extends UnaryNode {

	private int SIGN_FLIP = -1;

	public double evaluate(CharacterState state) throws SLogoException {
		return SIGN_FLIP * getChildren().get(0).evaluate(state);
	}
	
}