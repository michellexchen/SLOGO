package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

public class TurtlesNode extends TurtleCommand {

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return super.getWorkspace().getCharacterList().size();
	}

}