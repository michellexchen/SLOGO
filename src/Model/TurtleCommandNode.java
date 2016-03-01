package Model;

import Controller.SLogoException;
import Model.CommandNode;
import Model.Turtle;

/**
 * SLogo's TurtleCommandNode, an abstract class representing any Turtle Command that affects a TurtleState (TurtleCommand, Query currently)
 *
 */

public abstract class TurtleCommandNode extends CommandNode{

	private Turtle myTurtle;

	public void setTurtle(Turtle turtle){
		myTurtle = turtle;
	}
	
	public Turtle getTurtle(){
		return myTurtle;
	}
	
	public abstract double evaluate(CharacterState state) throws SLogoException;
}