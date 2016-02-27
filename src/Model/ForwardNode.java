package Model;

import java.util.ArrayList;
import Model.TurtleCommandNode;

public class ForwardNode extends TurtleCommandNode{
	private ArrayList<CommandNode> myChildren;
	private final static int NUM_CHILDREN = 1;
	
	public ForwardNode() {
		this.myChildren = new ArrayList<CommandNode>();
	}
	
	public double evaluate(CharacterState currentState) {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + newCoor[1]);
		return myChildren.get(0).evaluate(currentState);
	}
	
	private double[] calculateLoc(double direction, CharacterState currentState) {
		double[] result = new double[2];
		result[0] = Math.sin(direction)*myChildren.get(0).evaluate(currentState);
		result[1] = Math.cos(direction)*myChildren.get(0).evaluate(currentState); 
		return result;
	}

	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}