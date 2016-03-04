package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class RandomNode extends UnaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException {
		return Math.random()*getChildren().get(0).evaluate(state);
	}
	
}