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
 * DisplayData object is a container for each turtle's data
 * that is necessary for view to visualize
 * 
 * This Observable class exists to separate view and model and to ensure that
 * view does not have access to model's turtle objects
 * 
 * 
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
	private boolean cleared;
	private String myLineStyle = "SOLID";
	private SLogoCharacterState myState;

	public SLogoDisplayData(SLogoCharacterState state) throws SLogoException {
		myPosition = new SLogoPosition();
		myState = state;
		myPen = state.getPen();
		myLineStyle = "SOLID";

		initialize();
		updateData();
	}

	public void initialize() throws SLogoException {
		myLines = new ArrayList<Line>();
	}

	/**
	 * Called by command nodes to update the relevant display data
	 * Fields that get updated include PenData, direction, coordinates
	 * image, hidden (boolean), ID, clearing of trails, etc.
	 * 
	 * applyChanges method is called in the end to notify Observers
	 * 
	 * @param state
	 */
	public void updateData() {
		myPen = myState.getPen();
		prevDirection = myDirection;
		myDirection = myState.getDirection();
		myPosition.setXY(myState.getXCoor(), myState.getYCoor());
		myImage = myState.getImage();
		turtleHidden = myState.getHidden();
		ID = myState.getID();
		bgColor = myState.getBGColor();
		cleared = myState.getCleared();

		if (cleared) {
			myLines.clear();
		}
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
	
	public String getPenStyle(){
		return myLineStyle;
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

	public boolean isCleared(){
		return cleared;
	}

	public void setCleared(boolean cleared){
		myState.setCleared(cleared);
	}

}
