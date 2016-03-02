package Model;

import Controller.SLogoException;

public class XcorNode extends CommandNode{
	
	private int NUM_CHILDREN = 0;
	
	public XcorNode() {
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException{
		return state.getXCoor();
	}
}
