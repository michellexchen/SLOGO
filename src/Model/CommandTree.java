package Model;

import Controller.SLogoException;

/**
 * SLogo's CommandTree with access to root and self traversal to evaluate tree nodes
 *
 */

public class CommandTree {
	
	private Node root;
	
	public void traverse(CharacterState state) throws SLogoException{
		root.evaluate(state);
	}
	
	public void setRoot(Node root){
		this.root = root;
	}
	
	public Node getRoot(){
		return root;
	}

}