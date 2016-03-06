package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoTurtleState;

public class PenDownNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return ((SLogoTurtleState) state).getPen() ? 1 : 0;
	}
}