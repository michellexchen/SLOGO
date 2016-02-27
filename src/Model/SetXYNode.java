package Model;

import java.util.ArrayList;

public class SetXYNode extends TurtleCommandNode{
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 2;
	
	public SetXYNode() {
		myChildren = new ArrayList<CommandNode>();
	}
	
	public double evaluate(CharacterState currentState) {
		double distance = calculateDistance(currentState.getXCoor(), currentState.getYCoor(), currentState);
		currentState.setXCoor(myChildren.get(0).evaluate(currentState));
		currentState.setYCoor(myChildren.get(1).evaluate(currentState));
		return distance;
	}
	
	private double calculateDistance(double x, double y, CharacterState state) {
		return Math.sqrt(Math.pow(myChildren.get(0).evaluate(state) - x, 2) - Math.pow(myChildren.get(1).evaluate(state) - y, 2));
	}
	
	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}
