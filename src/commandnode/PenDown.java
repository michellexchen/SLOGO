package commandnode;

import model.SLogoCharacterState;

public class PenDown extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) {
		state.setPen(true);
		return 1;
	}

}
