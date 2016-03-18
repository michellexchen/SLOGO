package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetPenStyle extends UnaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		 double myIndex = evaluateChild(0, state);
	     state.getPen().setStrokeStyle(2);
	     return myIndex;
	}

}
