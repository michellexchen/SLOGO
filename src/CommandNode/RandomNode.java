package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class RandomNode extends CommandNode{
	private int NUM_CHILDREN = 1;

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