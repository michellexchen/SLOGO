package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class SetHeadingNode extends TurnNode{
	private final static int NUM_CHILDREN = 1;
	
	public SetHeadingNode(){
		setNumChildren(NUM_CHILDREN);
	}
	
	public double calculateDir(CharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state);
	}

}