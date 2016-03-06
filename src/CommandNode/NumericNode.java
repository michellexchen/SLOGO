package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

/**
 * SLogo's NumericNode, a class representing a numerical value (evaluate returns
 * the value) with no children
 *
 */

public class NumericNode extends NullaryNode {

	private double myValue;

	public NumericNode(double value) {
		myValue = value;
	}

	public double evaluate(SLogoCharacterState state) {
		return myValue;
	}

}