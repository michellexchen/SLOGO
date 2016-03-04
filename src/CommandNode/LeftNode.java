package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class LeftNode extends TurnNode {
	private int NUM_CHILDREN = 1;

	public LeftNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double calculateDir(CharacterState state) throws SLogoException {
		return state.getDirection() - getChildren().get(0).evaluate(state);
	}

}