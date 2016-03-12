package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class IsShowingNode extends NullaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return state.getHidden() ? 0 : 1;
	}

}