package commandnode;
import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of For command, a Control Structure command using variable
 */
public class ForNode extends BinaryVariableCommand {

	/**
	 * Children are two ListNode children
	 * Child one is a ListNode with four values, variable, start, end, and increment
	 * Child two is a ListNode with commands run for each value specified in range by a for-loop
	 * @return value of final command in list executed
	 * TODO use :variable in iteration
	 * Loop runs from start to end inclusive using increment between each run
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		ListNode controlList = ((ListNode) (getChildren().get(0)));
		SLogoVariable var = getWorkspace().createVariable(controlList.getInnerCommands().get(0), 1);
		int start = Integer.parseInt(controlList.getInnerCommands().get(1));
		int end = Integer.parseInt(controlList.getInnerCommands().get(2));
		int increment = Integer.parseInt(controlList.getInnerCommands().get(3));
		ListNode commandList = ((ListNode) (getChildren().get(1)));
		List<String> innerCommands = commandList.getInnerCommands();
		double evaluation = 0;
		for(int x=start; x<=end; x+=increment){
			List<Node> myRoots = getTreeFactory().createNodes(listCopy(innerCommands));
			getRootEvaluator().evaluateRoots(myRoots);
			var.setValue(x);
		}
		return evaluation;
	}

}