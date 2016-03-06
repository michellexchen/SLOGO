package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

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