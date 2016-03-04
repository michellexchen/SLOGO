package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class ShowingNode extends NullaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException{
		return (!state.getHidden()) ? 1 : 0;
	}
}