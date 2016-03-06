package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class PiNode extends NullaryNode {
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.PI;
	}

}