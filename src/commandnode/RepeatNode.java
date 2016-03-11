package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Repeat command, a Control Structure command using variable
 */
public class RepeatNode extends BinaryNode {

	/**
	 * Repeats commands in list expr number of times where expr is evaluation of child 0
	 * Child one is a ListNode, a list of commands in [ ]
	 * @return value of final command in list executed
	 * TODO use :repcount variable in valuation
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) evaluateChild(0, state);
		double evaluation = 0;
		for(int x=0; x<repcount; x++){
			evaluation = evaluateChild(1, state);
		}
		return evaluation;
	}

}