package model;

import javafx.scene.paint.Color;

/**
 * SLogo's TurtleState, a class extending abstract CharacterState to control
 * Turtle-only functions (currently pen)
 *
 */

public class SLogoTurtleState extends SLogoCharacterState {

	private boolean penDown;
	private Color penColor;
	private Pen myPen = new Pen();

	public SLogoTurtleState(Pen myPen, double xCoor, double yCoor, double direction, boolean isHidden, boolean penDown, double angle) {
		super(myPen, xCoor, yCoor, direction, isHidden, angle);
		this.penDown = penDown;
		penColor = Color.BLACK;
	}

	public void setPen(boolean penDown) {
		this.penDown = penDown;
	}

	public Pen getPen() {
		return myPen;
	}

	public boolean getPenDown(){
		return myPen.isPenDown();
	}
	
	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

}