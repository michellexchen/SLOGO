package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class RightNode extends TurnNode {
	private int NUM_CHILDREN = 1;

	public RightNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double calculateDir(double x, double y, CharacterState state) throws SLogoException {
		return -1 * getChildren().get(0).evaluate(state);
	}

}