package CommandNode;


import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class LessNode extends BinaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state) < getChildren().get(1).evaluate(state) ? 1 : 0;
	}
}