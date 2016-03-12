package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtle;

public class TurtlesNode extends TurtleCommand {

	/**
	 * returns the total number of turtles created so far by the turtle factory for the given workspace
	 */
	
	public TurtlesNode() {
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return super.getWorkspace().getCharacterList().size();
	}

}
