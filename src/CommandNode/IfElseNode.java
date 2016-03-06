package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class IfElseNode extends TernaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) != 0 ? getChildren().get(1).evaluate(state) : getChildren().get(2).evaluate(state);
	}

}