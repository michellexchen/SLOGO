package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * SLogo's CommandTree with access to root and self traversal to evaluate tree
 * nodes
 *
 */

public class CommandTree {

	private Node root;

	public double traverse(SLogoCharacterState state) throws SLogoException {
		return root.evaluate(state);
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

}