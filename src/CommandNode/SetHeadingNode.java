package CommandNode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetHeadingNode extends TurnNode{
	
	public double calculateDir(SLogoCharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state);
	}

}