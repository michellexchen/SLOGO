package Model.Query_Commands;

import Model.CharacterState;
import Model.CommandNode;

public abstract class TurtleQueryNode extends CommandNode {

	public void evaluate(CharacterState state){
		QueryEvaluate(state);
	}
	
	public abstract void QueryEvaluate(CharacterState state);

}