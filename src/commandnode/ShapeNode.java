package commandnode;

import model.SLogoCharacterState;

public class ShapeNode extends NullaryNode {
	
	public double evaluate(SLogoCharacterState state) {
		return state.getShapeIndex();
	}
}
