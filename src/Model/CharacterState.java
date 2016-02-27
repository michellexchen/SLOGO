package Model;

/**
 * SLogo's highest State in hierarchy, to support multiple types of character states
 *
 */

public abstract class CharacterState {
	
	private double xCoor;
	private double yCoor;
	private double direction;
	private boolean isHidden;
	
	public CharacterState(double xCoor, double yCoor, double direction, boolean isHidden){
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.direction = direction;
		this.isHidden = isHidden;
	}
	
	public void setHidden(boolean hide) {
		this.isHidden = hide;
	}
	
	public boolean getHidden(){
		return isHidden;
	}
	
	public void setXCoor(double xCoor){
		this.xCoor = xCoor;
	}
	
	public void setYCoor(double yCoor){
		this.yCoor = yCoor;
	}
	
	public double getXCoor(){
		return xCoor;
	}
	
	public double getYCoor(){
		return yCoor;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public void setDirection(double direction){
		this.direction = direction;
	}
}