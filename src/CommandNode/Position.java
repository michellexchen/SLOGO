package commandnode;

/**
 * A wrapper class for (x, y)-current and (x, y)-previous coordinates
 * 
 * Supports automatic center point (0, 0) conversion using Pane size
 * 
 * @author Hunter
 *
 */
public class Position {
	private static final int PANE_SIZE = 450;
	private static final int PADDING = 40;
	
	private double myWidth;
	private double myHeight;
	private double xPrevious;
	private double yPrevious;
	private double xCurrent;
	private double yCurrent;
	private double xCenter;
	private double yCenter;

	public Position(double width, double height) {
		// TODO Auto-generated constructor stub
		myWidth = width;
		myHeight = height;

		// set the center coordinates
		xCenter = width / 2;
		yCenter = height / 2;

		xPrevious = xCenter;
		yPrevious = yCenter;

		xCurrent = xCenter;
		yCurrent = yCenter;
	}

	
	public Position() {
		initialize();
	}
	
	public void initialize() {
		// TODO Auto-generated constructor stub
		myWidth = PANE_SIZE;
		myHeight = PANE_SIZE;

		// set the center coordinates
		xCenter = myWidth / 2;
		yCenter = myHeight / 2;

		xPrevious = xCenter;
		yPrevious = yCenter;

		xCurrent = xCenter;
		yCurrent = yCenter;
	}

	/**
	 * Sets new XY coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY(double x, double y) {
		xPrevious = xCurrent;
		yPrevious = yCurrent;
		
		xCurrent = x + xCenter;
		yCurrent = y + yCenter;
		
		if (xCurrent > PANE_SIZE - PADDING) {
			xCurrent = PANE_SIZE - PADDING;
		}
		if (xCurrent < PADDING) {
			xCurrent = PADDING;
		}
		if (yCurrent > PANE_SIZE - PADDING) {
			yCurrent = PANE_SIZE - PADDING;
		}
		if (yCurrent < PADDING) {
			yCurrent = PADDING;
		}
	}

	/////////////////////
	/// Getters and Setters
	/////////////////////

	public double getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(double myWidth) {
		this.myWidth = myWidth;
	}

	public double getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(double myHeight) {
		this.myHeight = myHeight;
	}

	public double xPrevious() {
		return xPrevious;
	}

	public void setxPrevious(double xPrevious) {
		this.xPrevious = xPrevious;
	}

	public double yPrevious() {
		return yPrevious;
	}

	public void setyPrevious(double yPrevious) {
		this.yPrevious = yPrevious;
	}

	public double xCurrent() {
		return xCurrent;
	}

	public void setxCurrent(double xCurrent) {
		this.xCurrent = xCurrent;
	}

	public double yCurrent() {
		return yCurrent;
	}

	public void setyCurrent(double yCurrent) {
		this.yCurrent = yCurrent;
	}

}
