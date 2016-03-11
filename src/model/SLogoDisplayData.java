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
public class SLogoDisplayData extends Observable{
	
	private SLogoPosition myPosition;
	private double myDirection;
	private String myImage;
	private List<Line> myLines;
	private boolean turtleHidden;
	private int ID;
	private Color bgColor;
	private SLogoPen myPen;
	private double prevDirection;

	/**
	 * Constructor that sets initial values
	 * 
	 * @param xCoordinate,
	 *            yCoordinate, angle, penDown, penColor, and Image
	 */
	public SLogoDisplayData(SLogoPosition position, double direction, boolean penDown, String image,
			SLogoPen pen, boolean turtleHidden, int ID, Color bgColor) {
		this.myPosition = position;
		this.myDirection = direction;
		this.myImage = image;
		this.turtleHidden = turtleHidden;
		this.ID = ID;
		this.bgColor = bgColor;
		this.myPen = pen;
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
		// TODO: Configure stroke
		prevDirection = myDirection;
		myDirection = state.getDirection();
		myPosition.setXY(state.getXCoor(), state.getYCoor());
		myImage = state.getImage();
		turtleHidden = state.getHidden();
		ID = state.getID();
		bgColor = state.getBGColor();
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
		return myPosition.getX();
	}

	public double getY() {
		return myPosition.getY();
	}

	public double getDirection() {
		return myDirection;
	}

	public SLogoPen getPen() {
		return myPen;
	}

	public String getImage() {
		return myImage;
	}

	public SLogoPosition getPosition() {
		return myPosition;
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

	public String getTurtleImage() {
		return myImage;
	}
	
	public boolean getTurtleHidden(){
		return turtleHidden;
	}
	
	public int getID(){
		return ID;
	}
	
	public Color getBGColor(){
		return bgColor;
	}

	public double getPrevDirection() {
		return prevDirection;
	}

}
