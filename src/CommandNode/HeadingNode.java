package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class HeadingNode extends NullaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException{
		return state.getDirection();
	}
}