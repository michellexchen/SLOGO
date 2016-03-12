package commandnode;

import java.util.List;
import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of Repeat command, a Control Structure command using variable
 */
public class RepeatNode extends BinaryVariableNode {

	/**
	 * Repeats commands in list expr number of times where expr is evaluation of child 0
	 * Child one is a ListNode, a list of commands in [ ]
	 * @return value of final command in list executed
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		int repcount = (int) evaluateChild(0, state);
		SLogoVariable repcountVar = getWorkspace().createVariable(new ResourceLoader().getString("Repcount"), 1);
		ListNode listNode = ((ListNode) (getChildren().get(1)));
		List<String> innerCommands = listNode.getInnerCommands();
		double evaluation = 0;
		for(int x=1; x<=repcount; x++){
			List<Node> myRoots = getTreeFactory().createNodes(clone(innerCommands));
			getRootEvaluator().evaluateRoots(myRoots);
			repcountVar.setValue(x);
		}
		return evaluation;
	}

}