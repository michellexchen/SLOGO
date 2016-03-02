package Model;

import Controller.SLogoException;

public class YcorNode extends CommandNode{
	
	private int NUM_CHILDREN = 0;
	
	public YcorNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		return state.getYCoor();
	}
}
