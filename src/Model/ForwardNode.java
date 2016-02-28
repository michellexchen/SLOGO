package Model;

import java.util.ArrayList;
import Model.TurtleCommandNode;

public class ForwardNode extends TurtleCommandNode{
	private final static int NUM_CHILDREN = 1;
	
	public double evaluate(CharacterState currentState) {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}
	
	private double[] calculateLoc(double direction, CharacterState currentState) {
		double[] result = new double[2];
		result[0] = Math.sin(direction)*getChildren().get(0).evaluate(currentState);
		result[1] = Math.cos(direction)*getChildren().get(0).evaluate(currentState); 
		return result;
	}

	public int getNumChildren() {
		return NUM_CHILDREN;
	}
}