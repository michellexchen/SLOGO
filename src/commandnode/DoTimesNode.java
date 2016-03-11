package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of DoTimes command, a Control Structure command using variable
 */
public class DoTimesNode extends BinaryNode {

	/**
	 * Children are two ListNode children
	 * Child one is a ListNode with two values, a variable and a limit
	 * Child two is a ListNode with commands run for each value in limit using variable
	 * @return value of final command in list executed
	 * TODO use :variable in iteration
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) evaluateChild(0, state);
		double evaluation = 0;
		for(int x=1; x<=repcount; x++){
			evaluation += evaluateChild(1, state);
		}
		return evaluation;
	}

}