package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class CosNode extends TrigNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double degrees = getChildren().get(0).evaluate(state);
		return cosTaylorApprox(degrees);
	}

}