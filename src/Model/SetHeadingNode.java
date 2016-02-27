package Model;

import java.util.ArrayList;

public class SetHeadingNode extends TurnNode{
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 1;
	
	public SetHeadingNode() {
		myChildren = new ArrayList<CommandNode>();
	}
	
	public double calculateDir(double x, double y, CharacterState state) {
		return myChildren.get(0).evaluate(state);
	}

	public int getNumChildren() {
		return NUM_CHILDREN;
	}

}
