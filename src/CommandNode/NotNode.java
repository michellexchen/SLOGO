package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class NotNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) == 0 ? 1 : 0;
	}
}