package Model;

import Model.CharacterState;

/**
 * SLogo's TurtleCommandNode, an abstract class representing any Turtle Query command
 *
 */

public abstract class TurtleQueryNode extends TurtleStateCommand {
	
	public double evaluate(){
		return QueryEvaluate(getTurtle().getState());
	}
	
	public abstract double QueryEvaluate(CharacterState state);

}