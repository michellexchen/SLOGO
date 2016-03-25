package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ClearStampsNode extends TurtleCommand{

	public ClearStampsNode() {
		setNumChildren(0);
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		super.clearStampTurtles();
		return 0;
	}

}
