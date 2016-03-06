package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class NotNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) == 0 ? 1 : 0;
	}
}