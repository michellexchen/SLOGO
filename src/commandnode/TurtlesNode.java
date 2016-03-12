package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

public class TurtlesNode extends TurtleCommand {

<<<<<<< HEAD
=======
	/**
	 * returns the total number of turtles created so far by the turtle factory for the given workspace
	 */
	
	public TurtlesNode() {
	}

	@Override
>>>>>>> 3508242dcd1a37328ab7a879d41e8ebd2ce14134
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return super.getWorkspace().getCharacterList().size();
	}

}