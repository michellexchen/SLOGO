package Model;

import java.util.ArrayList;

/**
 * SLogo's CommandNode, an abstract class representing any command (Turtle,
 * Query, Math, etc.)
 *
 */

public abstract class CommandNode implements Node {

	private ArrayList<Node> children = new ArrayList<Node>();
	private int NUM_CHILDREN;

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public int getNumChildren() {
		return NUM_CHILDREN;
	}
	
	public void setNumChildren(int num){
		NUM_CHILDREN = num;
	}

}