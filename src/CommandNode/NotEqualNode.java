package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class NotEqualNode extends EqualityNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return isEqual(state) ? 0 : 1;
	}
	
}