package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetPenColorNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double index = evaluateChild(0, state);
		state.getPen().setColor((int) index);
		return index;
	}
	
}