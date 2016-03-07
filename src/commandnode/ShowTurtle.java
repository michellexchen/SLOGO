package commandnode;

import model.SLogoCharacterState;

public class ShowTurtle extends NullaryNode{
 
	public double evaluate(SLogoCharacterState state) {
		state.setHidden(false);
		return 1;
	}
}
