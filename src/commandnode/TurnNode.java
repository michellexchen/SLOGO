package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Abstract class for commands that turn the turtle
 */

public abstract class TurnNode extends UnaryNode {
	
	/**
	 * @param state
	 * Updates direction of turtle
	 * @return change in direction (degrees)
	 */

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double newDirection = convertDir(calculateDir(state));
		double diff = Math.abs(newDirection - state.getDirection());
		state.setDirection(newDirection);
		return diff;
	}

	/**
	 * @param non-bounded direction (degrees)
	 * Converts direction to value between 0 and 360 
	 * @return bounded direction (degrees)
	 */
	
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