package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class SetHeadingNode extends TurnNode{
	
	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state);
	}

}