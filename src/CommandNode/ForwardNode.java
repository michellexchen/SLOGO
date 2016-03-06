package CommandNode;

import Model.*;
import View.*;
import Exception.*;
import Controller.*;
import deprecated_to_be_deleted.*;
import CommandNode.*;

public class ForwardNode extends UnaryNode {

	public double evaluate(SLogoCharacterState currentState) throws SLogoException {
		double[] newCoor = calculateLoc(currentState.getDirection(), currentState);
		currentState.setXCoor(currentState.getXCoor() + newCoor[0]);
		currentState.setYCoor(currentState.getYCoor() + newCoor[1]);
		return getChildren().get(0).evaluate(currentState);
	}

	private double[] calculateLoc(double direction, SLogoCharacterState currentState) throws SLogoException {
		double[] result = new double[2];
		result[0] = Math.sin(direction) * getChildren().get(0).evaluate(currentState);
		result[1] = -1*Math.cos(direction) * getChildren().get(0).evaluate(currentState);
		return result;
	}

}