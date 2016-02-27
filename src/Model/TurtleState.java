package Model;

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