package model;

import java.io.IOException;

import exception.SLogoException;

public class SLogoTurtleFactory {

	private SLogoWorkspace myWorkspace;
	private int LAST_ID = 0;
	private boolean DEFAULT_PEN_DOWN = true;
	private double DEFAULT_DIRECTION = 0; 
	private boolean DEFAULT_HIDDEN = false;
	private int DEFAULT_X = 0;
	private int DEFAULT_Y  = 0;
	private int DEFAULT_ID = 0;
	private String DEFAULT_IMAGE = "turtle_2.png";
	
	public SLogoTurtleFactory(SLogoWorkspace myWorkspace) {
		this.myWorkspace = myWorkspace;
	}
	
	public void createTurtle(int myID, int myX, int myY) throws SLogoException{
		SLogoPen myPen = new SLogoPen();
		SLogoTurtle myTurtle = new SLogoTurtle(myID, myPen, myX, myY, DEFAULT_PEN_DOWN, DEFAULT_DIRECTION, DEFAULT_HIDDEN, DEFAULT_IMAGE);
		myWorkspace.getCharacterList().add(myTurtle);
		SLogoDisplayData turtleData = new SLogoDisplayData(myTurtle.getState());

		// Add Observer (Visualizer)
		turtleData.addObserver(myWorkspace.getView().getObserver());
		myWorkspace.getObservableDataList().add(turtleData);
	}
	
	public void createDefaultTurtle() throws SLogoException{
		createTurtle(DEFAULT_X, DEFAULT_Y, LAST_ID++);
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