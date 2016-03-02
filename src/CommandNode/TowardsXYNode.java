package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class TowardsXYNode extends TurnNode {
	private final static int NUM_CHILDREN = 2;
	
	public TowardsXYNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double calculateDir(double x, double y, CharacterState state) throws SLogoException {
		double diffX = getChildren().get(0).evaluate(state) - x;
		double diffY = getChildren().get(1).evaluate(state) - y;
		return Math.atan(diffX / diffY);
	}
}