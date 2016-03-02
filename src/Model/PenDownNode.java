package Model;

import Controller.SLogoException;

public class PenDownNode extends CommandNode{
	
	private int NUM_CHILDREN = 0;
	
	public PenDownNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		return ((TurtleState) state).getPen() ? 1 : 0;
	}
}