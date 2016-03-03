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

	public void traverse(CharacterState state, Workspace workspace) throws SLogoException {
		root.evaluate(state, workspace); // also add the list
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public void setMyVars(String yo) {
	}
	
	public void setMyVars(Variable vars){
		this.myVars.add(vars);
	}

}