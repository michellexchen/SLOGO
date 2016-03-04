package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class YcorNode extends NullaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException{
		return state.getYCoor();
	}
	
}