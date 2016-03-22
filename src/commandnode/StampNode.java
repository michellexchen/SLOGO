package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class StampNode extends TurtleCommand{
	
	public StampNode(){
		setNumChildren(0);
	}

    /**
     * To be implemented
     * 
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
    	super.createStampTurtle(state);
        return 0;
    }

}