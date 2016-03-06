package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class MinusNode extends UnaryNode {

	private int SIGN_FLIP = -1;

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return SIGN_FLIP * getChildren().get(0).evaluate(state);
	}
	
}