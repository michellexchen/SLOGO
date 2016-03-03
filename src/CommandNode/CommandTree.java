package CommandNode;

import java.util.ArrayList;
import java.util.List;


import Exception.SLogoException;
import Model.CharacterState;
import Model.Variable;
import Model.Workspace;

/**
 * SLogo's CommandTree with access to root and self traversal to evaluate tree
 * nodes
 *
 */

public class CommandTree {

	private Node root;
	// have access to the list here of variables
	private List<Variable> myVars = new ArrayList<>();

	public void traverse(CharacterState state, CommandTree tree) throws SLogoException {
		if(root.grabType().equals("Control")){
			double val = root.evaluate(state, tree);
			myVars.get(myVars.size()-1).setValue(val);
		} else {
			root.evaluate(state); // also add the list
		}
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}
	
	public void setMyVars(Variable vars){
		this.myVars.add(vars);
	}

}