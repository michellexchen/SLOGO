package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class MinusNode extends UnaryNode {

	private int SIGN_FLIP = -1;

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return SIGN_FLIP * getChildren().get(0).evaluate(state);
	}
	
}