package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class TowardsXYNode extends TurnNode {

	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		double diffX = boundCoor(transformXCoor(getChildren().get(0).evaluate(state))) - state.getXCoor();
		double diffY = boundCoor(transformYCoor(getChildren().get(1).evaluate(state))) - state.getYCoor();
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
			return 90 + Math.toDegrees(Math.atan(diffX / diffY));
		}
	}
	
}