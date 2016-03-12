package model;

/**
 * SLogo's Position class for accessing previous and current X and Y coordinates
 * 
 * @author Hunter
 *
 */

public class SLogoPosition {
	
	private double myPrevX;
	private double myPrevY;
	private double myX;
	private double myY;

	/**
	 * Sets new XY coordinates and automatically updates previous X,Y values
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY (double x, double y) {		
		myPrevX = myX;
		myPrevY = myY;
		myX = x;
		myY = y;
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