package Model;

import Model.CommandNode;
import Model.Turtle;

/**
 * SLogo's TurtleCommandNode, an abstract class representing any Turtle Command that affects a TurtleState (TurtleCommand, Query currently)
 *
 */

public abstract class TurtleStateCommand extends CommandNode{

	private Turtle myTurtle;

	public TurtleStateCommand(Turtle myTurtle){
		this.myTurtle = myTurtle;
	}
	
	public void setTurtle(Turtle turtle){
		myTurtle = turtle;
	}
	
	public Turtle getTurtle(){
		return myTurtle;
	}
}