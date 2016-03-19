package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class StampNode extends TurtleCommand{
	
	public StampNode(){
		setNumChildren(0);
	}

    /**
     * Creates stamp of current turtle state (coordinates, direction, shape)
     * Returns shape index of stamped turtle
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
    	super.createStampTurtle(state);
        return state.getShapeIndex();
    }

}