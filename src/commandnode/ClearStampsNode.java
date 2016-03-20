package commandnode;

import java.util.List;

import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacter;
import model.SLogoCharacterState;

public class ClearStampsNode extends StampingNode{

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		List<SLogoCharacter> myCharacters = getWorkspace().getCharacterList();
		boolean stampWasCleared = false;
		for(SLogoCharacter character : myCharacters){
			if(character.getState().getID() == Integer.parseInt(new ResourceLoader().getString("StampID"))){
				character.getState().setHidden(true);
				stampWasCleared = true;
			}
		}
		return stampWasCleared ? 1 : 0;
	}

}