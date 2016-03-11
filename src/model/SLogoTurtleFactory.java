package model;

import exception.SLogoException;
import view.SLogoPropertiesData;

/**
 * SLogo's Turtle Factory class to create turtles
 * 
 */

public class SLogoTurtleFactory {

	private SLogoWorkspace myWorkspace;
	private int LAST_ID = 0;
	private boolean DEFAULT_PEN_DOWN = true;
	private double DEFAULT_DIRECTION = 0; 
	private boolean DEFAULT_HIDDEN = false;
	private int DEFAULT_X = 0;
	private int DEFAULT_Y  = 0;
	private int DEFAULT_ID = 0;
	private int DEFAULT_SHAPE_INDEX = 1;
	
	public SLogoTurtleFactory(SLogoWorkspace myWorkspace) {
		this.myWorkspace = myWorkspace;
	}
	
	public void createTurtle(int myX, int myY) throws SLogoException{
		SLogoPen myPen = new SLogoPen();
		SLogoTurtle myTurtle = new SLogoTurtle(LAST_ID++, myPen, myX, myY, DEFAULT_PEN_DOWN, DEFAULT_DIRECTION, DEFAULT_HIDDEN, DEFAULT_SHAPE_INDEX);
		myWorkspace.getCharacterList().add(myTurtle);
		SLogoDisplayData turtleData = new SLogoDisplayData(myTurtle.getState());

		// Add Observer (Visualizer)
		turtleData.addObserver(myWorkspace.getView().getObserver());
		myWorkspace.getObservableDataList().add(turtleData);
//		myWorkspace.addPen(myPen);
	}
	
	public void createDefaultTurtle() throws SLogoException{
		createTurtle(DEFAULT_X, DEFAULT_Y);
	}
		
	public int getDefaultX(){
		return DEFAULT_X;
	}
	
	public int getDefaultY(){
		return DEFAULT_Y;
	}
	
	public int getDefaultID(){
		return DEFAULT_ID;
	}

}