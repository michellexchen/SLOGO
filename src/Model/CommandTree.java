package Model;

import CommandNode.Node;
import Exception.SLogoException;

/**
 * SLogo's CommandTree with access to root and self traversal to evaluate tree
 * nodes
 *
 */

public class CommandTree {

	private Node root;

	public double traverse(CharacterState state) throws SLogoException {
		return root.evaluate(state);
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

}