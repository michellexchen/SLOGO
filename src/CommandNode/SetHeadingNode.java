package commandNode;

import exception.SLogoException;
import model.CharacterState;

public class SetHeadingNode extends TurnNode{
	private final static int NUM_CHILDREN = 1;
	
	public SetHeadingNode(){
		setNumChildren(NUM_CHILDREN);
	}
	
	public double calculateDir(double x, double y, CharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state);
	}

}