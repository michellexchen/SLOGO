package commandnode;

import java.util.List;
import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of Repeat command, a Control Structure command using variable
 */
public class RepeatNode extends BinaryVariableCommand {

	/**
	 * Repeats commands in list expr number of times where expr is evaluation of child 0
	 * Child one is a ListNode, a list of commands in [ ]
	 * @return value of final command in list executed
	 * TODO use :repcount variable in valuation
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) evaluateChild(0, state);
		SLogoVariable repcountVar = getWorkspace().createVariable(getResource("Repcount"), 1);
		List<String> innerCommands = ((ListNode) (getChildren().get(1))).getInnerCommands();
		double evaluation = 0;
		System.out.println("Repcount: " + repcount + " Command Parts: " + innerCommands);
		for(int x=0; x<repcount; x++){
			List<Node> myRoots = getTreeFactory().createNodes(innerCommands);
			repcountVar.setValue(repcountVar.getValue()+1);
			//evaluation = getRootEvaluator().evaluateRoot(myRoots);
		}
		return evaluation;
	}

}