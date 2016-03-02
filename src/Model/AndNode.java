package Model;

import Controller.SLogoException;

public class AndNode extends CommandNode {

	private int NUM_CHILDREN = 2;

	public AndNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		for (int x = 0; x < getChildren().size(); x++) {
			double expr = getChildren().get(x).evaluate(state);
			if (expr == 0)
				return 1;
		}
		return 0;
	}
}