package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class YcorNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return state.getYCoor();
	}
	
}