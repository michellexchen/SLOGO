package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class SumNode extends BinaryNode{

	public double evaluate(CharacterState state) throws SLogoException {
		System.out.println("SUM CHILDREN: " + getChildren().toString());
		return getChildren().get(0).evaluate(state) + getChildren().get(1).evaluate(state);
	}
	
}