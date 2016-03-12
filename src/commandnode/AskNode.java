package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class AskNode extends TellNode {

	/**
	 * works with the way that our workflow interacts to implement the ask [ turtles to set ] [ commands to evaluate ]
	 * by calling tellNode to implement itself and then allowing our current parsing of inputs to handle the rest of the
	 * command structure
	 */
	
	public AskNode() {
		super();
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
