package view;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.scene.shape.Line;
import model.Model;
import model.ResourceLoader;
import model.SLogoPosition;
import model.SLogoDisplayData;

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
		updatePropertiesPane();		
	}	
	
	/**
	 * This method updates turtles' attributes and position
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updatePropertiesPane () {
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
	
	/**
	 * @return the myGUIController
	 */
	public SLogoGUIController getGUIController() {
		return myGUIController;
	}

	/**
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

}