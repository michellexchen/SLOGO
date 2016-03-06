package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class XcorNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return state.getXCoor();
	}
	
}