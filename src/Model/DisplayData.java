/**
 * 
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * @author Hunter
 *
 */
public class DisplayData extends Observable {
	private Position myPosition;
	private double xCoordinate;
	private double yCoordinate;
	private double myAngle;
	private boolean penDown;
	private Color penColor;
	private ImageView myImage;

	private List<Line> myLines;

	/**
	 * Constructor that sets initial values
	 * 
	 * @param xCoordinate,
	 *            yCoordinate, angle, penDown, penColor, and Image
	 */
	public DisplayData(Position position, double angle, boolean penDown, Color penColor, ImageView image) {

		this.myPosition = position;
		this.myAngle = angle;
		this.penDown = penDown;
		this.penColor = penColor;
		this.myImage = image;
		
	}

	public DisplayData(CharacterState state) {
		updateData(state);
		initialize();
	}

	public void initialize () {
		myLines = new ArrayList<Line>();
	}
	
	public void updateData(CharacterState state) {
		myPosition = new Position();
		myPosition.setXY(state.getXCoor(),  state.getYCoor());
		myAngle = state.getAngle();
		penDown = ((TurtleState) state).getPen();
		penColor = ((TurtleState) state).getPenColor();
		myImage = state.getImageView();
		
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

	public double getAngle() {
		return myAngle;
	}

	public void setAngle(double myAngle) {
		// changed();
		this.myAngle = myAngle;
	}

	public boolean isPenDown() {
		return penDown;
	}

	public void setPenDown(boolean penDown) {
		// changed();
		this.penDown = penDown;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		// changed();
		this.penColor = penColor;
	}

	public ImageView getImage() {
		return myImage;
	}

	public void setImage(ImageView myImage) {
		// changed();
		this.myImage = myImage;
	}

	public Position getPosition() {
		return myPosition;
	}

	public void setPosition(Position myPosition) {
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

}
