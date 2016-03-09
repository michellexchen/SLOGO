package model;

public class TurtleFactory {

	private SLogoWorkspace myWorkspace;
	private int turtleID = 0;
	
	public TurtleFactory(SLogoWorkspace myWorkspace) {
		this.myWorkspace = myWorkspace;
	}
	
	public void createTurtle() {
		SLogoTurtle myTurtle = new SLogoTurtle(""+turtleID++, 0, 0, true, 0, false, 0);
		myWorkspace.getCharacterList().add(myTurtle);
		SLogoDisplayData turtleData = new SLogoDisplayData(myTurtle.getState());

		// Add Observer (Visualizer)
		turtleData.addObserver(myWorkspace.getView().getObserver());
		myWorkspace.getObservableDataList().add(turtleData);
	}

}
