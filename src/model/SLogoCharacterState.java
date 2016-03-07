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
	private Pen myPen;
	private double myAngle;
	private ImageView myImageView;

	public SLogoCharacterState(double xCoor, double yCoor, double direction,
					      boolean isHidden, double angle) {
		this.myXCoordinate = xCoor;
		this.myYCoordinate = yCoor;
		this.myDirection = direction;
		this.isHidden = isHidden;
		this.myAngle = angle;
		myImageView = new ImageView();
	}
	
	public Pen getPen() {
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

	public double getAngle() {
		return myAngle;
	}

	public void setAngle(double angle) {
		this.myAngle = angle;
	}

	public ImageView getImageView() {
		return myImageView;
	}

	public void setImageView(ImageView imageView) {
		this.myImageView = imageView;
	}
	
}