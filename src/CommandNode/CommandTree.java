package CommandNode;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Variable;

import Exception.SLogoException;
import Model.CharacterState;

/**
 * SLogo's CommandTree with access to root and self traversal to evaluate tree
 * nodes
 *
 */

public class CommandTree {

	private Node root;
	//have access to the list here of variables
	private List<Variable> myVars = new ArrayList<>();

	public void traverse(CharacterState state) throws SLogoException {
		root.evaluate(state); //also add the list
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}
	
	public void setMyVars(List<Variable> list){
		this.myVars = list;
	}

}