package commandnode;

import model.SLogoCharacterState;

public class PenDown extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) {
		state.getPen().setPenDown(true);
		return 1;
	}

}
