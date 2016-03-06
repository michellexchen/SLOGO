package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class PowNode extends BinaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double base = getChildren().get(0).evaluate(state);
		double exponent = getChildren().get(1).evaluate(state);
		return Math.pow(base, exponent);
	}
}