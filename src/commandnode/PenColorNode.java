package commandnode;

import model.SLogoCharacterState;

public class PenColorNode extends NullaryNode {
	
	public double evaluate(SLogoCharacterState state) {
		return state.getPen().getColorIndex();
	}
}
