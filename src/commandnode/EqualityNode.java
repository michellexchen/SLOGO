package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public abstract class EqualityNode extends BinaryNode{

	public boolean isEqual(SLogoCharacterState state) throws SLogoException{
		return evaluateChild(0, state) == evaluateChild(1, state) ? true : false;
	}

}