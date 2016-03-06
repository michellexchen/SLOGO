package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SinNode extends TrigNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.sin(getChildren().get(0).evaluate(state));
	}

}