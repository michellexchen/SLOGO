package model;

import javafx.scene.paint.Color;

/**
 * SLogo's TurtleState, a class extending abstract CharacterState to control
 * Turtle-only functions (currently pen)
 *
 */

public class TurtleState extends CharacterState {

	private boolean penDown;
	private Color penColor;

	public TurtleState(double xCoor, double yCoor, double direction, boolean isHidden, boolean penDown, double angle) {
		super(xCoor, yCoor, direction, isHidden, angle);
		this.penDown = penDown;
		penColor = Color.BLACK;
	}

	public void setPen(boolean penDown) {
		this.penDown = penDown;
	}

	public boolean getPen() {
		return penDown;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

}