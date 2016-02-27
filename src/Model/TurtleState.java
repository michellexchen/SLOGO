package Model;

/**
 * SLogo's TurtleState, a class extending abstract CharacterState to control Turtle-only functions (currently pen)
 *
 */

public class TurtleState extends CharacterState{

	private boolean penDown;
	
	public TurtleState(double xCoor, double yCoor, double direction, boolean isHidden, boolean penDown) {
		super(xCoor, yCoor, direction, isHidden);
		this.penDown = penDown;
	}
	
	public void setPen(boolean penDown) {
		this.penDown = penDown;
	}
	
	public boolean getPen(){
		return penDown;
	}
	
}