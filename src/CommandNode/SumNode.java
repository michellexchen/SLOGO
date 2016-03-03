package commandNode;

import exception.SLogoException;
import model.CharacterState;

public class SumNode extends CommandNode{
	private int NUM_CHILDREN = 2;

	public SumNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double result = 0;
//		System.out.println(getChildren().size());
//		System.out.println(getChildren().get(0));
//		System.out.println(getChildren().get(1));
		for(int x=0; x<getChildren().size(); x++){
			result += getChildren().get(x).evaluate(state);
		}
		return result;
	}
}