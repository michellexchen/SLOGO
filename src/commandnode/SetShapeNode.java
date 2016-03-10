package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetShapeNode extends UnaryNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double index = getChildren().get(0).evaluate(state);
		state.setShapeIndex((int) index); 
		return index;
	}
}
