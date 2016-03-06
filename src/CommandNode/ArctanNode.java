package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ArctanNode extends TrigNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.atan(getChildren().get(0).evaluate(state));
	}

}