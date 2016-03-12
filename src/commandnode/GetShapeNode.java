package commandnode;

import model.SLogoCharacterState;

public class GetShapeNode extends NullaryNode {
	
	public double evaluate(SLogoCharacterState state) {
		return state.getShapeIndex();
	}
}
