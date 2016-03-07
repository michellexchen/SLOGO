package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class IfElseNode extends TernaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) != 0 ? getChildren().get(1).evaluate(state)
				: getChildren().get(2).evaluate(state);
	}

}