package CommandNode;

import java.util.ArrayList;
import java.util.List;
import Exception.SLogoException;
import Model.CharacterState;
import Model.Variable;

/**
 * SLogo's CommandTree with access to root and self traversal to evaluate tree
 * nodes
 *
 */

public class CommandTree {

	private Node root;
	// have access to the list here of variables
	private List<Variable> myVars = new ArrayList<>();

	public double traverse(CharacterState state, CommandTree tree) throws SLogoException {
		double evaluation = 0;
		if(root.grabType().equals("Control")){
			evaluation = root.evaluate(state, tree);
			//grab the last added variable from our var list and set's the value mapped to the key 
			myVars.get(myVars.size()-1).setValue(evaluation);
		} else {
			evaluation = root.evaluate(state); // also add the list
		}
		return evaluation;
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