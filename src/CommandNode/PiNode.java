package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class PiNode extends NullaryNode {
	
	public double evaluate(CharacterState state) throws SLogoException {
		return Math.PI;
	}

}