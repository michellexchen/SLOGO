package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;
import Model.TurtleState;

public class PenDownNode extends NullaryNode{
	
	public double evaluate(CharacterState state) throws SLogoException{
		return ((TurtleState) state).getPen() ? 1 : 0;
	}
}