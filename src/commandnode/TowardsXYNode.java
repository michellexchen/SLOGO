package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TowardsXYNode extends TurnNode {
	
	private int NUM_CHILDREN = 2;
	
	public TowardsXYNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		double diffX = getChildren().get(0).evaluate(state) - state.getXCoor();
		double diffY = getChildren().get(1).evaluate(state) - state.getYCoor();
		if (diffX < 0) {
			if (diffY < 0) {
				 return 270 + Math.toDegrees(Math.atan(diffX / diffY));
			}
			return 270 - Math.toDegrees(Math.atan(diffX / diffY));
		}
		else {
			if (diffY < 0) {
				 return 90 - Math.toDegrees(Math.atan(diffX / diffY));
			}
			return Math.toDegrees(Math.atan(diffX/diffY));
		}
	}
	
}