package commandnode;

import model.SLogoCharacterState;

public class HideTurtle extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) {
		state.setHidden(true);
		return 0;
	}
}
