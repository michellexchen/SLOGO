// This entire file is part of my masterpiece.
// Michelle Chen

/** I chose to refactor and submit the SLogoDisplayData and SLogoPropertiesVisualizer classes as my masterpiece because I believe that these two classes showcase my understanding 
 * of the Observable/Observer design pattern. The SLogoDisplayData class is Observable-- it contains each turtle's data for the view to visualize while separating the 
 * view and model to ensure that view doesn't have access to model's turtle objects. PenData, direction, coordinates image, hidden (boolean), ID, clearing of trails, etc 
 * for each turtle are updated through the updateData() method that utilizes a SLogoCharacterState. I wanted to focus on the properties pane in the UI being dynamically 
 * updated through the Observable/Observer interface, so I extracted the relevant variables/methods from the SLogoVisualizer class and created a new class, SLogoPropertiesVisualizer, 
 * for my masterpiece. This was done both because the code masterpiece is limiting in its allowance of line numbers, but also because extracting just the properties makes for code that 
 * is easier to manage. The SLogoPropertiesVisualizer class contains methods that renders turtles and showcases properties on the screen; I elaborate on this in the SLogoPropertiesVisualizer class.
 * 
 * The purpose of this class is largely to set up and update relevant turtle properties for the observers to utilize. I think that it showcases what I've learned so far in the course beyond
 * just things like utilizing encapsulation or not having code smells. The usage of the Observer/Observable interface was a design decision made by my team after much deliberation 
 * and I believe that it ultimately helped to reduce dependencies/cut out direct references. Here, the model is a subtype of Observable and the view is a subtype of Observer—and these two classes 
 * together handle the automatic notification function of MVC, providing the mechanism for views to be automatically notified of changes in the model. Any time something in a model class 
 * changes, it simply sends an update to all other classes that observe it through notifyObservers(). Although other classes depend on the model, the model itself does not require references
 * to other classes and can continue to do its job without worrying about outside dependencies on it. This one-to-many dependency between objects encapsulates the core components in an 
 * Observable abstraction, and the variable components in an Observer hierarchy. This explicit separation between view and model makes for a clear division between model objects and 
 * presentation objects the user sees—these model objects are thus completely self contained and work without reference to the presentation objects. 
 */


package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import exception.SLogoException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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

    public SLogoPosition getPosition() {
        return myPosition;
    }

    public List<Line> getLines() {
        return myLines;
    }


    public int getID(){
        return myID;
    }

    public Color getBGColor(){
        return myBGColor;
    }

    public boolean areLinesCleared(){
        return isCleared;
    }

    public void queueLineClearing(boolean cleared){
        myState.queueLineClearing(cleared);
    }
}
