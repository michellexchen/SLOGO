package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class XcorNode extends NullaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException{
		return state.getXCoor();
	}
	
}