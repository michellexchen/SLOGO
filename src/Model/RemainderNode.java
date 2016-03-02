package Model;

import Controller.SLogoException;

public class RemainderNode extends CommandNode{
	private int NUM_CHILDREN = 2;

	public RemainderNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState state) throws SLogoException {
		double result = getChildren().get(0).evaluate(state);
		for(int x=1; x<getChildren().size(); x++){
			result %= getChildren().get(x).evaluate(state);
		}
		return result;
	}
}