package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ClearstampsNode extends TurtleCommand{

	public ClearstampsNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		super.clearStampTurtles();
		return 0;
	}

}
