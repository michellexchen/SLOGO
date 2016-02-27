package Model.Turtle_Commands;

import Model.CharacterState;
import Model.CommandNode;

public abstract class TurtleCommandNode extends CommandNode {

	public void evaluate(CharacterState state){
		TurtleEvaluate(state);
	}
	
	public abstract double TurtleEvaluate(CharacterState state);

}