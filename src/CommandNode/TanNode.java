package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TanNode extends TrigNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double degrees = getChildren().get(0).evaluate(state);
		return sinTaylorApprox(degrees) / cosTaylorApprox(degrees);
	}

}