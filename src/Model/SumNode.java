package Model;

import Controller.SLogoException;

public class SumNode extends CommandNode{
	private int NUM_CHILDREN = 2;

	public SumNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double result = 0;
		for(int x=0; x<getChildren().size(); x++){
			result += getChildren().get(x).evaluate(state);
		}
		return result;
	}
}