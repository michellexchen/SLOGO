/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import exception.SLogoException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * @author Hunter
 *
 */
public class SLogoDisplayData extends Observable {
	
	private SLogoPosition myPosition;
	private double xCoordinate;
	private double yCoordinate;
	private double myDirection;
	private SLogoPen myPen;
	private boolean penDown;
	private Color penColor;
	private double penSize;
	private String myImage;
	private List<Line> myLines;
	private boolean turtleHidden;
	private int ID;

	/**
	 * Constructor that sets initial values
	 * 
	 * @param xCoordinate,
	 *            yCoordinate, angle, penDown, penColor, and Image
	 */
	public SLogoDisplayData(SLogoPosition position, double direction, boolean penDown, Color penColor, String image,
			SLogoPen pen, boolean turtleHidden, int ID) {
		this.myPosition = position;
		this.myDirection = direction;
		this.penDown = penDown;
		this.penColor = penColor;
		this.myImage = image;
		this.myPen = pen;
		this.turtleHidden = turtleHidden;
		this.ID = ID;
	}

	public SLogoDisplayData(SLogoCharacterState state) throws SLogoException {
		myPosition = new SLogoPosition();
		updateData(state);
		initialize();
	}

	public void initialize() throws SLogoException {
		myLines = new ArrayList<Line>();
	}

	public void updateData(SLogoCharacterState state) {
		myPen = state.getPen();
		penDown = myPen.isPenDown();
		penColor = myPen.getPenColor();
		penSize = myPen.getThickness();
		// TODO: Configure stroke
		myDirection = state.getDirection();
		myPosition.setXY(state.getXCoor(), state.getYCoor());
		xCoordinate = state.getXCoor();
		yCoordinate = state.getYCoor();
		myImage = state.getImage();
		turtleHidden = state.getHidden();
		ID = state.getID();
		applyChanges();
	}

	/**
	 * Set the hasState value of DisplayData as 'changed' and notify all
	 * Observers
	 * 
	 * @param None
	 */
	private void applyChanges() {
		setChanged();
		notifyObservers();
	}

	public void addLine(Line newline) {
		myLines.add(newline);
	}

	public double getX() {
		return xCoordinate;
	}

	public void setX(double xCoordinate) {
		// changed();
		this.xCoordinate = xCoordinate;
	}

	public double getY() {
		return yCoordinate;
	}

	public void setY(double yCoordinate) {
		// changed();
		this.yCoordinate = yCoordinate;
	}

	public double getDirection() {
		return myDirection;
	}

	public void setDirection(double myDirection) {
		// changed();
		this.myDirection = myDirection;
	}

	public boolean isPenDown() {
		return penDown;
	}

	public void setPenDown(boolean penDown) {
		this.penDown = penDown;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

	public String getImage() {
		return myImage;
	}

	public void setImage(String myImage) {
		this.myImage = myImage;
	}

	public SLogoPosition getPosition() {
		return myPosition;
	}

	public void setPosition(SLogoPosition myPosition) {
		this.myPosition = myPosition;
	}

	/**
	 * @return the myLines
	 */
	public List<Line> getLines() {
		return myLines;
	}

	/**
	 * @param myLines the myLines to set
	 */
	public void setLines(List<Line> myLines) {
		this.myLines = myLines;
	}

	public double getPenSize() {
		return penSize;
	}

	public String getTurtleImage() {
		return myImage;
	}
	
	public boolean getTurtleHidden(){
		return turtleHidden;
	}
	
	public int getID(){
		return ID;
	}

}
