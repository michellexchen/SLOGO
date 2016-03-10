package commandnode;

import model.SLogoCharacterState;

public class HideTurtleNode extends NullaryNode{
	
	public double evaluate(SLogoCharacterState state) {
		state.setHidden(true);
		return 0;
	}
}
