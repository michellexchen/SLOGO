package commandNode;

import exception.SLogoException;
import model.CharacterState;
import model.TurtleState;

public class PenDownNode extends CommandNode{
	
	private int NUM_CHILDREN = 0;
	
	public PenDownNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		return ((TurtleState) state).getPen() ? 1 : 0;
	}
}