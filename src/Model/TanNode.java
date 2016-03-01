package Model;

import Controller.SLogoException;

public class TanNode extends TrigNode{

	public double evaluate(CharacterState state) throws SLogoException {
		double degrees = getChildren().get(0).evaluate(state);
		return sinTaylorApprox(degrees) / cosTaylorApprox(degrees);
	}

}