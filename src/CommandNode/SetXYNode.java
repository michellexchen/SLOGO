package commandNode;

import exception.SLogoException;
import model.CharacterState;

public class SetXYNode extends TurtleCommandNode {
	private final static int NUM_CHILDREN = 2;
	
	public SetXYNode(){
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState currentState) throws SLogoException {
		double distance = calculateDistance(currentState.getXCoor(), currentState.getYCoor(), currentState);
		currentState.setXCoor(Math.abs(getChildren().get(0).evaluate(currentState) + 500)); //Replace 500 with X midpoint
		currentState.setYCoor(Math.abs(getChildren().get(1).evaluate(currentState) - 500)); //Replace 500 with Y midpoint
		return distance;
	}

	private double calculateDistance(double x, double y, CharacterState state) throws SLogoException {
		return Math.sqrt(Math.pow(getChildren().get(0).evaluate(state) + 500 - x, 2) //Replace 500 with X midpoint
				- Math.pow(Math.abs(getChildren().get(1).evaluate(state) - 500) - y, 2)); //Replace 500 with Y midpoint
	}
}