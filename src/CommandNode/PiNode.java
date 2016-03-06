package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class PiNode extends NullaryNode {
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.PI;
	}

}