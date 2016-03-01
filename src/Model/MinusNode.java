package Model;

import Controller.SLogoException;

public class MinusNode extends CommandNode{
	private int NUM_CHILDREN = 1;

	public MinusNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double result = 0;
		for(int x=0; x<getChildren().size(); x++){
			result += getChildren().get(x).evaluate(state);
		}
		return -1*result;
	}
}