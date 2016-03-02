package Model;

import Controller.SLogoException;

public class PowNode extends CommandNode{

	private int NUM_CHILDREN = 2;
	public PowNode(){
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException {
		double base = getChildren().get(0).evaluate(state);
		double pow = 1;
		double exponent = getChildren().get(1).evaluate(state);
		for(int x=0; x<exponent; x++){
			pow *= base;
		}
		return pow;
	}
}