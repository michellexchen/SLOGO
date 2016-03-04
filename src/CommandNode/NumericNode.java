package CommandNode;

import Model.CharacterState;

/**
 * SLogo's NumericNode, a class representing a numerical value (evaluate returns
 * the value) with no children
 *
 */

public class NumericNode extends UnaryNode {

	private double myValue;

	public NumericNode(double value) {
		myValue = value;
	}

	public double evaluate(CharacterState state) {
		return myValue;
	}

}