package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetPenSizeNode extends UnaryNode{
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double pixels = getChildren().get(0).evaluate(state);
		state.getPen().setThickness(pixels);
		return pixels;
	}
}
