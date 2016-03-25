package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;

/** Ask, Tell's node representation
 *  To be implemented
 *
 */
public class AskNode extends TellNode {

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		List<SLogoCharacter> originalList = getWorkspace().getActiveTurtlesList();
		for(SLogoCharacter each: originalList){
		}
		super.evaluate(state);
		return 0;
	}

	
	
}