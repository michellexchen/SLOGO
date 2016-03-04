package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class SetHeadingNode extends TurnNode{
	
	public double calculateDir(CharacterState state) throws SLogoException {
		return getChildren().get(0).evaluate(state);
	}

}