package Model;

import java.util.ArrayList;

public class LeftNode {
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 1;
	
	public LeftNode() {
		myChildren = new ArrayList<CommandNode>();
	}
	
	public double calculateDir(double x, double y) {
		return myChildren.get(0).evaluate();
	}
	
	public int getNumChildren() {
		return NUM_CHILDREN;
	}

}
