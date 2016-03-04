package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class IfNode extends CommandNode {

	private int NUM_CHILDREN = 2;
	
	public IfNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double expr = getChildren().get(0).evaluate(state);
		if(expr != 0){
			return getChildren().get(1).evaluate(state);
		}
		return 0;
	}

}