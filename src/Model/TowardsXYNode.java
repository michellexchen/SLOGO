package Model;

import java.util.ArrayList;

public class TowardsXYNode extends TurnNode{
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 2;
	
	public TowardsXYNode() {
		myChildren = new ArrayList<CommandNode>();
	}
	
	public double calculateDir(double x, double y) {
		double diffX = myChildren.get(0).evaluate() - x;
		double diffY = myChildren.get(1).evaluate() - y;
		return Math.atan(diffX/diffY);
	}
	
	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}
