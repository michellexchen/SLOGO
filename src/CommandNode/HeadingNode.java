package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class HeadingNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return state.getDirection();
	}
}