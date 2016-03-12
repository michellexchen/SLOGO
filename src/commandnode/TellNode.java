package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtle;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;

public class TellNode extends TurtleCommand {
	
	private static final int PROGRAMMINGINDEXING = 1;
	
	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		super.getWorkspace().resetActiveTurtles();
		List<Node> commands = ((ListNode) getChildren().get(0)).getCommands();
		List<SLogoCharacter> activeTurtles = super.getWorkspace().getActiveTurtlesList();
		for(Node command: commands){
			double turtleToGrab = ((NumericNode)command).getNumericValue();
			//creating the turtle from factory automatically adds our turtle to our list of created turtles
			SLogoCharacter newActiveTurtle = super.grabTurtle((int)turtleToGrab-PROGRAMMINGINDEXING);
			super.getWorkspace().addActiveTurtle(newActiveTurtle);
		}
		//return the id of the last created active turtle
		return ((SLogoTurtle)activeTurtles.get(activeTurtles.size()-PROGRAMMINGINDEXING)).getCurrTurtlesID();
	}
}