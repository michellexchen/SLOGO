package CommandNode;


import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;
public class ShowingNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException{
		return (!state.getHidden()) ? 1 : 0;
	}
}