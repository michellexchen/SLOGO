package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of DoTimes command, a Control Structure command using variable
 */
public class DoTimesNode extends BinaryVariableCommand {

	/**
	 * Children are two ListNode children
	 * Child one is a ListNode with two values, a variable and a limit
	 * Child two is a ListNode with commands run for each value in limit using variable
	 * @return value of final command in list executed
	 * TODO use :variable in iteration
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		ListNode controlList = ((ListNode) (getChildren().get(0)));
		SLogoVariable var = getWorkspace().createVariable(controlList.getInnerCommands().get(0), 1);
		double limit = Double.parseDouble(controlList.getInnerCommands().get(1));
		ListNode commandList = ((ListNode) (getChildren().get(1)));
		List<String> innerCommands = commandList.getInnerCommands();
		double evaluation = 0;
		for(int x=0; x<limit; x++){
			List<Node> myRoots = getTreeFactory().createNodes(listCopy(innerCommands));
			getRootEvaluator().evaluateRoots(myRoots);
			var.setValue(x);
		}
		return evaluation;
	}

}