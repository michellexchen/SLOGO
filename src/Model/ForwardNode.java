package Model;

import Controller.SLogoException;
import Model.TurtleCommandNode;

public class ForwardNode extends TurtleCommandNode {
	private int NUM_CHILDREN = 1;

	public ForwardNode() {
		setNumChildren(NUM_CHILDREN);
	}

	public double evaluate(CharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}

	private double[] calculateLoc(double direction, CharacterState currentState) throws SLogoException {
		double[] result = new double[2];
		result[0] = Math.sin(direction) * getChildren().get(0).evaluate(currentState);
		result[1] = Math.cos(direction) * getChildren().get(0).evaluate(currentState);
		return result;
	}

	public String toString() {
		return "Forward";
	}

}