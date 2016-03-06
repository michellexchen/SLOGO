package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public abstract class EqualityNode extends BinaryNode{

	public boolean isEqual(SLogoCharacterState state) throws SLogoException{
		return getChildren().get(0).evaluate(state) == getChildren().get(1).evaluate(state) ? true : false;
	}

}