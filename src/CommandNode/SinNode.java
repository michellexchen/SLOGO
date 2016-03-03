package commandNode;

import exception.SLogoException;
import model.CharacterState;

public class SinNode extends TrigNode{

	public double evaluate(CharacterState state) throws SLogoException {
		// for 1 < X < 1, sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...
		double degrees = getChildren().get(0).evaluate(state);
		return sinTaylorApprox(degrees);
	}

}