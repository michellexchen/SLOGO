package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of TowardsXY, a Turtle Command
 */
public class TowardsXYNode extends TurnNode {
	
	private int NUM_CHILDREN = 2;
	
	public TowardsXYNode() throws SLogoException{
		setNumChildren(NUM_CHILDREN);
	}

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		double diffX = evaluateChild(0, state) - state.getXCoor();
		double diffY = evaluateChild(1, state) - state.getYCoor();
		if (diffY < 0) {
			return Math.toDegrees(Math.atan(diffX/diffY)) - 180;
		}
		return Math.toDegrees(Math.atan(diffX/diffY));
	}
	
}