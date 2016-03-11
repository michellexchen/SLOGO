package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of DoTimes command
 * Children are two ListNode children representing list of variables and commands
 * Runs commands for each value in variable list
 */
public class DoTimesNode extends BinaryNode {

	/**
	 * @param state, SLogoCharacterState to act upon
	 * @return value of final command executed in command list
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