package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class DoTimesNode extends CommandNode {

	private int NUM_CHILDREN = 2;

	public DoTimesNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		int repcount = (int) getChildren().get(0).evaluate(state);
		double evaluation = 0;
		for(int x=1; x<=repcount; x++){
			evaluation += getChildren().get(1).evaluate(state);
		}
		return evaluation;
	}

}