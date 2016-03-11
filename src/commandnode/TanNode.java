package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TanNode extends UnaryNode{

	public TanNode() throws SLogoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return Math.tan(Math.toRadians(evaluateChild(0, state)));
	}

}