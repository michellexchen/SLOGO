package commandnode;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;

public class TurtleCommand extends UnaryNode {

	private SLogoWorkspace myWorkspace;
	private SLogoTurtleFactory turtleFactory;
	
	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setWorkspace(SLogoWorkspace ws){
		myWorkspace = ws;
		turtleFactory = ws.getCurrentTurtleFactory();
	}
	
	public SLogoTurtleFactory getTurtleFactory() {
		return turtleFactory;
	}
	
	public SLogoWorkspace getWorkspace(){
		return myWorkspace;
	}
	
	protected SLogoCharacter grabTurtle(int turtleIndexToGrab) throws SLogoException{
		return turtleFactory.createTurtle(turtleFactory.getDefaultX(), turtleFactory.getDefaultY(), turtleIndexToGrab);
	}

}
