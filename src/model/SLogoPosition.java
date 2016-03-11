package model;
public class SLogoPosition {
	
	private double myPrevX;
	private double myPrevY;
	private double myX;
	private double myY;

	/**
	 * Sets new XY coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY (double x, double y) {		
		myPrevX = myX;
		myPrevY = myY;
		myX = x;
		myY = y;
		System.out.println("Prev: " + myPrevX + "  " + myPrevY);
		System.out.println("Current " + myX + "  " + myY);
	}

	public double getPrevX() {
		return myPrevX;
	}

	public double getPrevY() {
		return myPrevY;
	}
	public double getX() {
		return myX;
	}

	public double getY() {
		return myY;
	}
}