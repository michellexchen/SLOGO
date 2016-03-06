package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class LeftNode extends TurnNode {

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		return state.getDirection() - getChildren().get(0).evaluate(state);
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}


}