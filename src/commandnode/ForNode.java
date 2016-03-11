package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of For command, a Control Structure command using variable
 */
public class ForNode extends QuaternionNode {

	/**
	 * Children are two ListNode children
	 * Child one is a ListNode with four values, variable, start, end, and increment
	 * Child two is a ListNode with commands run for each value specified in range by a for-loop
	 * @return value of final command in list executed
	 * TODO use :variable in iteration
	 * Loop runs from start to end inclusive using increment between each run
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return 0; // TEMP
	}

}