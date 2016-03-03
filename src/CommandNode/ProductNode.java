package commandNode;

import exception.SLogoException;
import model.CharacterState;

public class ProductNode extends CommandNode{
	private int NUM_CHILDREN = 2;

	public ProductNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double result = 1;
		for(int x=0; x<getChildren().size(); x++){
			result *= getChildren().get(x).evaluate(state);
		}
		return result;
	}
}