// This entire file is part of my masterpiece.
// Michelle Chen

/**
 * The SLogoPropertiesVisualizer class observes SLogoDisplayData to update turtle attributes. It reads in a resource file and updates properties dynamically and also has ties to placing
 * turtles on canvases and drawing lines. Its method updatePropertiesPane() is called in GUIController in order to dynamically update turtle properties in the properties pane of the UI. 
 * Though not included in my code masterpiece, in my refactoring I also wrote a line factory class and slogoturtle image class to extract methods from SLogoPropertiesVisualizer which 
 * makes SLogoPropertiesVisualizer much less cluttered and generalizes it out. It encapsulates and delegates specific jobs to other classes, making it clear which class does what. 
 * I mention this here because I think these are also important design patterns--the factory pattern, for example, generalizes a functionality out and makes it reusable in more than one
 * context which not only decreases duplicated code but also gets rid of certain convoluted dependencies.
 * 
 * When anything in the SLogoDisplayData class changes, it sends an update to the classes that observe it (in this case, SLogoPropertiesVisualizer). This makes for good design because 
 * SLogoDisplayData doesn't need to know that SLogoPropertiesVisualizer is observing it; this sort of dependency allows the visualizer to do its job and render turtles/showcase properties 
 * without having access to the model. As mentioned in the other class, this explicit separation between view and model makes for a clear division--these model objects are thus 
 * completely self contained and work without reference to the presentation objects.  
 */

package view;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.scene.shape.Line;
import model.Model;
import model.ResourceLoader;
import model.SLogoPosition;
import model.SLogoDisplayData;

/**
 * Properties Visualizer class that contains methods that renders turtles and showcases properties on the screen
 * 
 * @author Michelle
 *
 */
public class SLogoPropertiesVisualizer implements Observer {
	
	private final String RESOURCE_LOCATION = "resources/visualization";
	private ResourceBundle infoResource = ResourceBundle.getBundle(RESOURCE_LOCATION);
	private final int PANE_SIZE = Integer.parseInt(infoResource.getString("PaneSize"));
	private final int DIRECTION_FLIP = Integer.parseInt(infoResource.getString("DirectionFlip"));
	private final int DEFAULT_STROKE = Integer.parseInt(infoResource.getString("DefaultStroke"));

	private SLogoGUIController myGUIController;
	private Model myModel;
	private SLogoPropertiesData myProperties = new SLogoPropertiesData();
	private LineFactory lineFactory = new LineFactory(PANE_SIZE, DIRECTION_FLIP, DEFAULT_STROKE);
		
	@Override
	public void update(Observable o, Object arg) {
		updateProperties();		
	}	
	
	/**
	 * This method updates turtles' attributes and position
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updateProperties () {
		getGUIController().getCanvas().getChildren().clear();
		for (SLogoDisplayData turtledata : getModel().getObservableDataList()) {
			placeTurtle(turtledata);
			if(turtledata.getID() != Integer.parseInt(new ResourceLoader().getString("StampID"))){
				turtledata.addLine(createLine(turtledata));
				getGUIController().addToCanvas(turtledata.getLines());
			}
			getGUIController().updateProperties(turtledata);
			myProperties.setPaneColor(turtledata.getBGColor());
		}
	}
	
	/**
	 * Creates a Line object with default color black through the usage of a line factory
	 * 
	 * @param position
	 * @return Line
	 */
	public Line createLine(SLogoPosition position) {
		Line newLine = lineFactory.makeLine(position, myGUIController.getCustomizer().getMyPenColor());
		myGUIController.getCustomizer().changeStroke(newLine);
		return newLine;
	}
	
	/**
	 * Creates a Line object with specified color
	 * 
	 * @param position
	 * @param color
	 * @return Line
	 */
	public Line createLine(SLogoDisplayData turtledata) {
		Line newLine = createLine(turtledata.getPosition());
		newLine.setFill(turtledata.getPen().getColor());
		newLine.setStrokeWidth(turtledata.getPen().getSize());
		if(!turtledata.getPen().getDown() || turtledata.areLinesCleared()){
			newLine.setStrokeWidth(0);
			if(turtledata.areLinesCleared()) {
				turtledata.queueLineClearing(false);
			}
		}
		return newLine;
	}
		
	/**
	 * Places a turtle on canvas through usage of SLogoTurtleImage class
	 * 
	 * @param displaydata
	 */
	public void placeTurtle(SLogoDisplayData displaydata) {
		SLogoTurtleImage turtle = new SLogoTurtleImage(infoResource, displaydata);
		turtle.initialize();
		getGUIController().addToCanvas(turtle);
	}

	public SLogoGUIController getGUIController() {
		return myGUIController;
	}

	public Model getModel() {
		return myModel;
	}

}