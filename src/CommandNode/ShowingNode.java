package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ShowingNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return (!state.getHidden()) ? 1 : 0;
	}
}