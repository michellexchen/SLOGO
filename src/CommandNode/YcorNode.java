package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class YcorNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return state.getYCoor();
	}
	
}