package Model.Turtle_Commands;

import Model.CharacterState;
import Model.Turtle;

/**
 * SLogo's TurtleCommandNode, an abstract class representing any Turtle Command
 *
 */

public abstract class TurtleCommandNode extends TurtleStateCommand {
	
	public TurtleCommandNode(Turtle turtle){
		super(turtle);
	}

	public void evaluate(){
		TurtleEvaluate(getTurtle().getState());
	}
	
	public abstract double TurtleEvaluate(CharacterState state);

}