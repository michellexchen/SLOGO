package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ClearscreenNode extends NullaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		state.setCleared(true);
		HomeNode home = new HomeNode();
		return home.evaluate(state);
	}

}