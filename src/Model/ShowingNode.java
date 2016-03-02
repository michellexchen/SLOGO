package Model;

import Controller.SLogoException;

public class ShowingNode extends CommandNode{
	
	private int NUM_CHILDREN = 0;
	
	public ShowingNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		return (!state.getHidden()) ? 1 : 0;
	}
}