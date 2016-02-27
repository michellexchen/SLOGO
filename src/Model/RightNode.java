package Model;

import java.util.ArrayList;

public class RightNode extends TurnNode{
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 1;
	
	public RightNode() {
		myChildren = new ArrayList<CommandNode>();
	}
	
	public double calculateDir(double x, double y, CharacterState state) {
		return -myChildren.get(0).evaluate(state);
	}
	
	public int getNumChildren() {
		return NUM_CHILDREN;
	}

}
