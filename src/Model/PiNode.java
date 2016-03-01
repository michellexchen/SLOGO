package Model;

import Controller.SLogoException;

public class PiNode extends CommandNode {
	private int NUM_CHILDREN = 0;

	public PiNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		return 3.141592653589793238462643383279;
	}

}