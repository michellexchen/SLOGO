package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public abstract class EqualityNode extends BinaryNode{

	public boolean isEqual(CharacterState state) throws SLogoException{
		return getChildren().get(0).evaluate(state) == getChildren().get(1).evaluate(state) ? true : false;
	}

}