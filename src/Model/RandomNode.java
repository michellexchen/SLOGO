package Model;

import Controller.SLogoException;

public class RandomNode extends CommandNode{
	private int NUM_CHILDREN = 0;

	public RandomNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double result = getChildren().get(0).evaluate(state);
		if(result < 0){
			return 0;
		}
		return result;
	}
}