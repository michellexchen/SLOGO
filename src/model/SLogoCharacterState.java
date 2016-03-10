package model;

import javafx.scene.image.ImageView;

/**
 * SLogo's highest State in hierarchy, to support multiple types of character
 * states
 *
 */

public abstract class SLogoCharacterState {

	private double myXCoordinate;
	private double myYCoordinate;
	private double myDirection;
	private boolean isHidden;
	private SLogoPen myPen;
	private String myImage;

	public SLogoCharacterState(SLogoPen myPen, double xCoor, double yCoor, double direction,
					      boolean isHidden, String myImage) {
		this.myPen = myPen;
		this.myXCoordinate = xCoor;
		this.myYCoordinate = yCoor;
		this.myDirection = direction;
		this.isHidden = isHidden;
		this.myImage = myImage;
	}
	
	public SLogoPen getPen() {
		return myPen;
	}

	public void setHidden(boolean hide) {
		this.isHidden = hide;
	}

	public boolean getHidden() {
		return isHidden;
	}

	public void setXCoor(double xCoor) {
		this.myXCoordinate = xCoor;
	}

	public void setYCoor(double yCoor) {
		this.myYCoordinate = yCoor;
	}

	public double getXCoor() {
		return myXCoordinate;
	}

	public double getYCoor() {
		return myYCoordinate;
	}

	public double getDirection() {
		return myDirection;
	}

	public void setDirection(double direction) {
		this.myDirection = direction;
	}

	public String getImage() {
		return myImage;
	}

	public void setImageView(String myImage) {
		this.myImage = myImage;
	}
	
}