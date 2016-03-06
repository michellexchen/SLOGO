package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SumNode extends BinaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		System.out.println("SUM CHILDREN: " + getChildren().toString());
		return getChildren().get(0).evaluate(state) + getChildren().get(1).evaluate(state);
	}
	
}