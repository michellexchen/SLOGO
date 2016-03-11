package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

public abstract class TurnNode extends UnaryNode {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double newDirection = convertDir(calculateDir(state));
		double diff = Math.abs(newDirection - state.getDirection());
		state.setDirection(newDirection);
		return diff;
	}

		public double convertDir(double direction) {
			double result;
			if (direction > 360) {
				result = direction % 360;
			}
			else if(direction < 0) {
				result = 360 - (Math.abs(direction) % 360);
			}
			else {
				result = direction;
			}
			return result;
		}

	public abstract double calculateDir(SLogoCharacterState state) throws SLogoException;

}