package model;

import javafx.scene.image.ImageView;

/**
 * SLogo's highest State in hierarchy, to support multiple types of character
 * states
 *
 */

public abstract class SLogoCharacterState {

	private double xCoor;
	private double yCoor;
	private double direction;
	private boolean isHidden;
	private double angle;
	private ImageView imageView;
	private boolean penDown;

	public SLogoCharacterState(double xCoor, double yCoor, double direction,
					      boolean isHidden, double angle) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.direction = direction;
		this.isHidden = isHidden;
		this.angle = angle;
		imageView = new ImageView();
	}

	public void setHidden(boolean hide) {
		this.isHidden = hide;
	}

	public boolean getHidden() {
		return isHidden;
	}

	public void setXCoor(double xCoor) {
		this.xCoor = xCoor;
	}

	public void setYCoor(double yCoor) {
		this.yCoor = yCoor;
	}

	public double getXCoor() {
		return xCoor;
	}

	public double getYCoor() {
		return yCoor;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	public void setPen(boolean penDown) {
		this.penDown = penDown;
	}
	
}