package Model;

import Model.CharacterState;

/**
 * SLogo's TurtleCommandNode, an abstract class representing any Turtle Command
 *
 */

public abstract class TurtleCommandNode extends TurtleStateCommand {

	public double evaluate(){
		return TurtleEvaluate(getTurtle().getState());
	}
	
	public abstract double TurtleEvaluate(CharacterState state);

}