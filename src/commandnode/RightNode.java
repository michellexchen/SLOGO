package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class RightNode extends TurnNode {

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		return state.getDirection() + evaluateChild(0, state);
	}

}