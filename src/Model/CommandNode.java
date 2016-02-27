package Model;

import java.util.ArrayList;

/**
 * SLogo's CommandNode, an abstract class representing any command (Turtle, Query, Math, etc.)
 *
 */

public abstract class CommandNode implements Node{

	private ArrayList<Node> children = new ArrayList<Node>();

	public ArrayList<Node> getChildren(){
		return children;
	}

	public void addChild(Node child){
		children.add(child);
	}
	
}