package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public abstract class EqualityNode extends BinaryNode{

	public boolean isEqual(SLogoCharacterState state) throws SLogoException{
		return getChildren().get(0).evaluate(state) == getChildren().get(1).evaluate(state) ? true : false;
	}

}