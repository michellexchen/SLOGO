package CommandNode;

import Exception.SLogoException;
import Model.CharacterState;

public class NotNode extends CommandNode{

	private int NUM_CHILDREN = 1;
	
	public NotNode(){
		setNumChildren(NUM_CHILDREN);
	}
	
	public double evaluate(CharacterState state) throws SLogoException {
		for(int x=0; x<getChildren().size(); x++){
			double expr = getChildren().get(x).evaluate(state);
			if(expr != 0)
				return 0;
		}
		return 1;
	}
}