package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Super class for forward and backward commands
 */

public abstract class StraightCommand extends UnaryNode{
	
	/**
	 * @param direction: turtle's current direction
	 * Calculates new turtle position
	 * @return result: 1x2 Array of new (x, y)
	 */

	public double[] calculateLoc(double direction, SLogoCharacterState state) throws SLogoException {
		double[] result = new double[2];
		result[0] = Math.sin(Math.toRadians(direction)) * evaluateChild(0, state);
		result[1] = Math.cos(Math.toRadians(direction)) * evaluateChild(0, state);
		result[0] = precisionFix(result[0]);
		result[1] = precisionFix(result[1]);
		return result;
	}
	
	/**
	 * Rounds coordinate if small error
	 */

	private double precisionFix(double value){
		return (double)Math.round(value * 100000d) / 100000d;
	}

}