package commandNode;

import exception.SLogoException;
import model.CharacterState;

public class HeadingNode extends CommandNode{
	
	private int NUM_CHILDREN = 0;
	
	public HeadingNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		return state.getDirection();
	}
}