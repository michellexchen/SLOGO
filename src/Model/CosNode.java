package Model;

import Controller.SLogoException;

public class CosNode extends TrigNode{

	public double evaluate(CharacterState state) throws SLogoException {
		double degrees = getChildren().get(0).evaluate(state);
		return cosTaylorApprox(degrees);
	}

}