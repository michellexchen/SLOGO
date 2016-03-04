package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class LeftNode extends TurnNode {

	public double calculateDir(CharacterState state) throws SLogoException {
		return state.getDirection() - getChildren().get(0).evaluate(state);
	}

}