package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TowardsXYNode extends TurnNode {

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		double diffX = Math.abs(evaluateChild(0, state) + 500 - state.getXCoor());
		double diffY = Math.abs(Math.abs(evaluateChild(1, state) - 500) - state.getYCoor());
		if (evaluateChild(0, state) < 0) {
			if (evaluateChild(1, state) < 0) {
				return -180 + Math.atan(diffX / diffY);
			}
			return -1 * Math.atan(diffX / diffY);
		}
		else {
			if (evaluateChild(1, state) < 0) {
				return 180 - Math.atan(diffX / diffY);
			}
			return Math.atan(diffX / diffY);
		}
	}
	
}