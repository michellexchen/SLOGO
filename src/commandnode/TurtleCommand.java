package commandnode;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;

public class TurtleCommand extends UnaryNode {

	/**
	 * Super class that is extended by all turtle command classes. A concise and abstract way of extending functionality for
	 * turtle commands who need access to the list of active and all turtles contained within the workspace
	 */
	
	private SLogoWorkspace myWorkspace;
	private SLogoTurtleFactory turtleFactory;
	
	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * set the workspace
	 * 
	 * @param SLogoWorkspace sw - current workspace 
	 */
	public void setWorkspace(SLogoWorkspace ws){
		myWorkspace = ws;
		turtleFactory = ws.getCurrentTurtleFactory();
	}
	
	/**
	 * get the SLogoTurtleFactory
	 * 
	 * @param SLogoTurtleFactory turtleFactory - current turtleFactory 
	 */
	public SLogoTurtleFactory getTurtleFactory() {
		return turtleFactory;
	}
	
	/**
	 * get the workspace
	 * 
	 * @return SLogoWorkspace sw - current workspace 
	 */
	public SLogoWorkspace getWorkspace(){
		return myWorkspace;
	}
	
	/**
	 * closed way of allowing the super class to handle interacting with the specific turtle factory instance in order to 
	 * create and return a turtle with a specific index
	 * 
	 * @param int turtleIndexToGrab - index of turtle that we want
	 * @return SLogoCharacter 
	 */
	protected SLogoCharacter grabTurtle(int turtleIndexToGrab) throws SLogoException{
		return turtleFactory.createTurtle(turtleFactory.getDefaultX(), turtleFactory.getDefaultY(), turtleIndexToGrab);
	}

}
