package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetPenSizeNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double pixels = evaluateChild(0, state);
		state.getPen().setThickness(pixels);
		return pixels;
	}
}