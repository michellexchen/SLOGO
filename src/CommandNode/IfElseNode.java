package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class IfElseNode extends CommandNode {

private int NUM_CHILDREN = 3;
	
	public IfElseNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double expr = getChildren().get(0).evaluate(state);
		if(expr != 0){
			return getChildren().get(1).evaluate(state);
		}
		return getChildren().get(2).evaluate(state);
	}

}