package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoTurtle;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;

public class TellNode extends BinaryNode {
	
	private SLogoWorkspace myWorkspace;
	private SLogoTurtleFactory turtleFactory;

	public TellNode() {
		
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		List<Node> commands = ((ListNode) getChildren().get(0)).getCommands();
//		for(Node each: apples) System.out.println(each.getClass());
		List<SLogoCharacter> allTurtles = myWorkspace.getCharacterList();
		List<SLogoCharacter> activeTurtles = myWorkspace.getActiveTurtlesList();
		//check to make sure all necessary turtles are created and inside the characterList
		while(commands.get(commands.size()-1).evaluate(state) < allTurtles.size()){ 
			//can also check by seeing what turtelfactory's last created id is
			SLogoCharacter newActiveTurtle = turtleFactory.createDefaultTurtle();
			myWorkspace.addActiveTurtle(newActiveTurtle);
			allTurtles.add(newActiveTurtle);
		}
		//now must add the execution of commands for active list 
		//this is the second way that Tell can be used --> tell [ no. turtles ] [ executable commands ]
		
		//return the id of the last created active turtle
		return ((SLogoTurtle)activeTurtles.get(activeTurtles.size()-1)).getCurrTurtlesID();
	}
	
	public SLogoWorkspace getWorkspace(){
		return myWorkspace;
	}

	public void setWorkspace(SLogoWorkspace ws){
		myWorkspace = ws;
	}

	
}
