package Model;

import java.util.ArrayList;

public class SetXYNode {
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 2;
	
	public SetXYNode() {
		myChildren = new ArrayList<CommandNode>();
	}
	
	public double TurtleEvaluate(CharacterState currentState) {
		double distance = calculateDistance(currentState.getXCoor(), currentState.getYCoor());
		currentState.setXCoor(myChildren.get(0).evaluate());
		currentState.setYCoor(myChildren.get(1).evaluate());
		return distance;
	}
	
	private double calculateDistance(double x, double y) {
		return Math.sqrt(Math.pow(myChildren.get(0).evaluate() - x, 2) - Math.pow(myChildren.get(1).evaluate() - y, 2));
	}
	
	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}
