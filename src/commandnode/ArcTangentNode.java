package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of ArcTan, Atan commands
 */
public class ArcTangentNode extends UnaryNode {

	/**
	 * @return ArcTangent evaluation of radians converted from degrees
	 * throws SLogoException if degrees out of ArcTangent domain
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double evaluation = evaluateChild(0, state);
		if(evaluation > 90 || evaluation < 90){
			throw new SLogoException(getInstruction("ArcTan"));
		}
		return Math.atan(Math.toRadians(evaluateChild(0, state)));
	}

}