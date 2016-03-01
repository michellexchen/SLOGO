package Model;

import Controller.SLogoException;

public class AddNode extends CommandNode {

	private static final int NUM_CHILDREN = 1;

	public AddNode() {
		setNumChildren(NUM_CHILDREN);
	}

	@Override
	public double evaluate(CharacterState state) throws SLogoException {
		double result = 0;
		for (int x = 0; x < getChildren().size(); x++) {
			result += getChildren().get(x).evaluate(state);
		}
		return result;
	}

}
