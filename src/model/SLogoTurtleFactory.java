package model;

import exception.SLogoException;

/**
 * SLogo's Turtle Factory class to create turtles
 * 
 */
public class SLogoTurtleFactory {

	private SLogoWorkspace myWorkspace;
	private int LAST_ID = 0;
	private double DEFAULT_DIRECTION = 0; 
	private boolean DEFAULT_HIDDEN = false;
	private int DEFAULT_X = 0;
	private int DEFAULT_Y  = 0;
	private int DEFAULT_ID = 0;
	private int DEFAULT_SHAPE_INDEX = 1;
	
	public SLogoTurtleFactory(SLogoWorkspace myWorkspace) {
		this.myWorkspace = myWorkspace;
	}
	
	/**
	 * @param myX
	 * @param myY
	 * @param requestedID
	 * Initializes a turtle with given ID. Makes it available to frontend. If turtle already initialized and is currently contained in the workspace
	 * then that turtle instance is grabbed and added to the active turtles list
	 * @return myTurtle: created turtle
	 */
	
	public SLogoCharacter createTurtle(int myX, int myY, int requestedID) throws SLogoException{
		if(hasTurtleBeenCreated(requestedID)){
			return myWorkspace.getCharacterList().get(requestedID);
		}
		SLogoPen myPen = new SLogoPen();
		SLogoTurtle myTurtle = new SLogoTurtle(LAST_ID++, myPen, myX, myY, DEFAULT_DIRECTION, DEFAULT_HIDDEN, DEFAULT_SHAPE_INDEX);
		myWorkspace.getCharacterList().add(myTurtle);
		SLogoDisplayData turtleData = new SLogoDisplayData(myTurtle.getState());
		turtleData.addObserver(myWorkspace.getView().getObserver());
		myWorkspace.getObservableDataList().add(turtleData);
		return myTurtle;
	}
	
	/**
	 * Checks whether ID is available
	 */
	private boolean hasTurtleBeenCreated(int requestedID){
		return (requestedID < LAST_ID);
	}
	
	/**
	 * Creates a turtle with default parameters
	 */
	
	public SLogoCharacter createDefaultTurtle() throws SLogoException{
		return createTurtle(DEFAULT_X, DEFAULT_Y, LAST_ID);
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