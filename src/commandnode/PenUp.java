package commandnode;

import model.SLogoCharacterState;

public class PenUp extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) {
		state.getPen().setPenDown(false);
		return 0;
	}
}
