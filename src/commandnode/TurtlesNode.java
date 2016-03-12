package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtle;

public class TurtlesNode extends TurtleCommand {

	public TurtlesNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		// return super.evaluate(state);
		List<SLogoCharacter> activeTurtles = getWorkspace().getActiveTurtlesList();
//		return ((SLogoTurtle) activeTurtles.get(activeTurtles.size() - 1)).getState().getID();
		return super.getWorkspace().getCharacterList().size();
	}

}
