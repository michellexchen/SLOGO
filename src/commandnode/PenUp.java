package commandnode;

import model.SLogoCharacterState;

public class PenUp extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) {
		state.setPen(false);
		return 0;
	}
}
