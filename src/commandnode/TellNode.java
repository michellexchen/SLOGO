package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtle;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;

public class TellNode extends TurtleCommandNode {
	


	public TellNode() {
		
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		List<Node> commands = ((ListNode) getChildren().get(0)).getCommands();
//		for(Node each: apples) System.out.println(each.getClass());
		List<SLogoCharacter> allTurtles = super.getWorkspace().getCharacterList();
		List<SLogoCharacter> activeTurtles = super.getWorkspace().getActiveTurtlesList();
		//check to make sure all necessary turtles are created and inside the characterList
		Node num = commands.get(commands.size()-1);
		System.out.println(((NumericNode)num).getNumericValue());
		System.out.println(allTurtles.size());
		while(((NumericNode)num).getNumericValue() > allTurtles.size()){ 
			//can also check by seeing what turtelfactory's last created id is
			SLogoCharacter newActiveTurtle = super.getTurtleFactory().createDefaultTurtle();
			//creating the turtle from factory automatically adds our turtle to our list of created turtles
			super.getWorkspace().addActiveTurtle(newActiveTurtle);
		}
		//now must add the execution of commands for active list 
		//this is the second way that Tell can be used --> tell [ no. turtles ] [ executable commands ]
		
		//return the id of the last created active turtle
		return ((SLogoTurtle)activeTurtles.get(activeTurtles.size()-1)).getCurrTurtlesID();
	}
}
