package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class StampNode extends TurtleCommand{
	
	private static final int STAMP_ID = -1;
	
	public StampNode(){
		setNumChildren(0);
	}

    /**
     * To be implemented
     * 
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
    	System.out.println(numRequiredChildren());
    	super.createStampTurtle(state);
        return 0;
    }

}