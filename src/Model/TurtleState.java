package Model;

public class TurtleState implements CharacterState {
	private double xCoor;
	private double yCoor;
	private boolean penDown;
	private double direction;
	private boolean isHidden;

	public TurtleState(double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.penDown = penDown;
		this.direction = direction;
		this.isHidden = isHidden;
	}
	
	public CharacterState move() {
		
	}
	
	public CharacterState rotate() {
		
	}
	
	public CharacterState penDown() {
		
	}
	
	public CharacterState penUp() {
		
	}
	
	public CharacterState setHidden(boolean hide) {
		this.isHidden = hide;
	}

//	public void updateState(TurtleState newState) {
//		xCoor = newState.getXCoor();
//		yCoor = newState.getYCoor();
//		penDown = newState.getPenDown();
//		direction = newState.getDirection();
//		isHidden = newState.getHidden();
//	}

	
}