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
 * @author Hunter, Michelle
 *
 */
public class SLogoDisplayData extends Observable{

    private SLogoPosition myPosition;
    private double myDirection;
    private String myImage;
    private List<Line> myLines;
    private boolean isTurtleHidden;
    private int myID;
    private Color myBGColor;
    private SLogoPen myPen;
    private double myPrevDirection;
    private boolean isCleared;
    private String myLineStyle;
    private SLogoCharacterState myState;

    /**
     * Default constructor that sets up DisplayData basic data fields
     * 
     * @param state
     * @throws SLogoException
     */
    public SLogoDisplayData(SLogoCharacterState state) throws SLogoException {
        myPosition = new SLogoPosition();
        myState = state;
        myPen = state.getPen();
        myLines = new ArrayList<>();
        updateData();
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
        myPrevDirection = myDirection;
        myDirection = myState.getDirection();
        myPosition.setXY(myState.getXCoor(), myState.getYCoor());
        myImage = myState.getImage();
        isTurtleHidden = myState.getHidden();
        myID = myState.getID();
        myBGColor = myState.getBGColor();
        isCleared = myState.getCleared();        
        if (isCleared) {
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

    public void setX(double x) {
        myPosition.setX(x);
    }

    public void setY(double y) {
        myPosition.setY(y);
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

    public List<Line> getLines() {
        return myLines;
    }

    public String getTurtleImage() {
        return myImage;
    }

    public boolean getTurtleHidden(){
        return isTurtleHidden;
    }

    public int getID(){
        return myID;
    }

    public Color getBGColor(){
        return myBGColor;
    }

    public double getPrevDirection() {
        return myPrevDirection;
    }

    public boolean areLinesCleared(){
        return isCleared;
    }

    public void queueLineClearing(boolean cleared){
        myState.queueLineClearing(cleared);
    }
}
