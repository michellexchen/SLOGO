package commandnode;

import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacterState;

public class StampNode extends StampingNode{

	/**
	 * Creates stamp of current turtle state (coordinates, direction, shape)
	 * Returns shape index of stamped turtle
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		getTurtleFactory().createTurtle((int)state.getXCoor(), (int)state.getYCoor(), state.getDirection(), state.getShapeIndex(), Integer.parseInt(new ResourceLoader().getString("StampID")));
		return state.getShapeIndex();
	}

}