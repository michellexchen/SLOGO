package Model;

import Model.CharacterState;
import Model.Turtle;

/**
 * SLogo's TurtleCommandNode, an abstract class representing any Turtle Query command
 *
 */

public abstract class TurtleQueryNode extends TurtleStateCommand {

	public TurtleQueryNode(Turtle turtle){
		super(turtle);
	}

	public void evaluate(){
		QueryEvaluate(getTurtle().getState());
	}
	
	public abstract double QueryEvaluate(CharacterState state);

}