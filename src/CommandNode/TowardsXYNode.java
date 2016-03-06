package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TowardsXYNode extends TurnNode {

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		double diffX = Math.abs(getChildren().get(0).evaluate(state) + 500 - state.getXCoor());
		double diffY = Math.abs(Math.abs(getChildren().get(1).evaluate(state) - 500) - state.getYCoor());
		if (getChildren().get(0).evaluate(state) < 0) {
			if (getChildren().get(1).evaluate(state) < 0) {
				return -180 + Math.atan(diffX / diffY);
			}
			return -1 * Math.atan(diffX / diffY);
		}
		else {
			if (getChildren().get(1).evaluate(state) < 0) {
				return 180 - Math.atan(diffX / diffY);
			}
			return Math.atan(diffX / diffY);
		}
	}
	
}